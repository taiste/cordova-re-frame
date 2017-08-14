(ns template.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [template.events]
            [template.subs]
            [template.views :as views]
            [template.config :as config]))

(defn splashscreen []
  (js/console.log "Splashscreen")
  (.-splashscreen js/navigator))

(defn hide-splashscreen []
  (js/console.log "Hide splashscreen")
  (when-let [splash (splashscreen)]
    (.hide splash)))

(defn dev-setup []
  (js/console.log "Dev setup")
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (js/console.log "Mount root")
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export onDeviceReady []
  (js/console.log "Device Ready")
  (hide-splashscreen)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root)
  (-> (js* "navigator")
    (.-notification)
      (.alert "Device Native Bridge works!"
      (fn [] nil)
      ""
      "")))

(defn ^:export prepare-device []
  (js/console.log "Prepare device")
  (.addEventListener js/document "deviceready" onDeviceReady false))

(defn ^:export init []
  (js/console.log "Init")
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))


(js/console.log "Starting...")
; (prepare-device)
(if js/window.cordova (prepare-device) (init))
