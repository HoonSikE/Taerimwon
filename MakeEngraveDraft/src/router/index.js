import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EngraveView from '../views/EngraveView.vue'
import AboutView from '../views/AboutView.vue'

import EngraveCreateView from '../components/engrave/EngraveCreateView.vue'
import EngraveCreateDetail from '../components/engrave/create/EngraveCreateDetail.vue'

import TabletCreateView from '../components/tablet/TabletCreateView.vue'

import ResultView from '../components/result/ResultView.vue'
import ResultEngrave from '../components/result/fullScreen/ResultEngrave.vue'
import ResultTablet from '../components/result/fullScreen/ResultTablet.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: HomeView,
      redirect: '/engrave/engraveCreate',
    },
    {
      path: '/engrave',
      name: 'engrave',
      component: EngraveView,
      redirect: '/engrave/engraveCreate',
      children: [
        {
          path: 'engraveCreate',
          name: 'engraveCreate',
          component: EngraveCreateView,
          redirect: '/engrave/engraveCreate/engraveDetail?type=일반&selectedType=일반&showRouterView=true',
          children: [
            {
              path: 'engraveDetail',
              name: 'engraveDetail',
              component: EngraveCreateDetail,
              props: (route) => ({
                type: route.query.type,
                selectedType: route.query.selectedType,
                showRouterView: route.query.showRouterView,
              }),
              children: [
                {
                  path: 'tabletCreate',
                  name: 'TabletCreateView',
                  component: TabletCreateView,
                  props: (route) => ({
                    type: route.query.type,
                    name1: route.query.name1,
                    name2: route.query.name2,
                    date1: route.query.date1,
                    date1Type: route.query.date1Type,
                    date2: route.query.date2,
                    date2Type: route.query.date2Type,
                    selectedType: route.query.selectedType,
                  }),
                },
              ],
            },
          ],
        },
        {
          path: 'result',
          name: 'result',
          component: ResultView,
          props: (route) => ({
            type: route.query.type,
            name0: route.query.name0,
            name1: route.query.name1,
            name2: route.query.name2,
            date1: route.query.date1,
            date1Type: route.query.date1Type,
            date2: route.query.date2,
            date2Type: route.query.date2Type,
            selectedType: route.query.selectedType,
            selectedType2: route.query.selectedType2,
          }),
        },
        {
          path: 'resultEngrave',
          name: 'resultEngrave',
          component: ResultEngrave,
          props: (route) => ({
            type: route.query.type,
            name0: route.query.name0,
            name1: route.query.name1,
            name2: route.query.name2,
            date1: route.query.date1,
            date1Type: route.query.date1Type,
            date2: route.query.date2,
            date2Type: route.query.date2Type,
            selectedType: route.query.selectedType,
            selectedType2: route.query.selectedType2,
          })
        },
        {
          path: 'resultTablet',
          name: 'resultTablet',
          component: ResultTablet,
          props: (route) => ({
            type: route.query.type,
            name0: route.query.name0,
            name1: route.query.name1,
            name2: route.query.name2,
            date1: route.query.date1,
            date1Type: route.query.date1Type,
            date2: route.query.date2,
            date2Type: route.query.date2Type,
            selectedType: route.query.selectedType,
            selectedType2: route.query.selectedType2,
          })
        },
      ],
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    }
  ]
})

export default router
