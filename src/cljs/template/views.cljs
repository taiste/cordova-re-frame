(ns template.views
  (:require
    [cljsjs.material-ui]
    [cljs-react-material-ui.core :refer [get-mui-theme color]]
    [cljs-react-material-ui.reagent :as ui]
    [cljs-react-material-ui.icons :as ic]
    [re-frame.core :as re-frame]
    [reagent.core :as r]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [ui/mui-theme-provider
        {:mui-theme (get-mui-theme
          {:palette {:text-color (color :green600)}})}
        [:div
          [ui/app-bar { :title "Title"
                        :icon-element-right
                        (r/as-element [ui/icon-button
                        (ic/action-account-balance-wallet)])}]
          [ui/paper
            [:div "Hello"]]
          [ui/paper
            [ui/mui-theme-provider
              {:mui-theme (get-mui-theme {:palette {:text-color (color :blue200)}})}
            [ui/raised-button {:label "Blue button"}]]
            (ic/action-home {:color (color :grey600)})
            [ui/raised-button { :label        "Click me"
                                :icon         (ic/social-group)
                                :on-touch-tap #(println "clicked")}]]]])))
