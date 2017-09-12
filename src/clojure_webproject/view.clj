(ns clojure-webproject.view
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate v-page "page.html"
  [page]
  [:title] (html/content (:title page))
  [:h1] (html/content (:title page))
  [:div.page-body] (html/content (:body page)))
