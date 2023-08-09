import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EngraveView from '../views/EngraveView.vue'
import AboutView from '../views/AboutView.vue'
import EngraveCreateView from '../components/engrave/EngraveCreateView.vue'
import EngraveCreate1 from '../components/engrave/create/EngraveCreate1.vue'
import EngraveCreate2 from '../components/engrave/create/EngraveCreate2.vue'
import EngraveCreate3 from '../components/engrave/create/EngraveCreate3.vue'
import EngraveCreate4 from '../components/engrave/create/EngraveCreate4.vue'
import EngraveCreate5 from '../components/engrave/create/EngraveCreate5.vue'
import EngraveCreate6 from '../components/engrave/create/EngraveCreate6.vue'
import EngraveResult from '../components/engrave/EngraveResult.vue'
import EngraveSample from '../components/engrave/EngraveSample.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: HomeView
    },
    {
      path: '/engrave',
      name: 'engrave',
      component: EngraveView,
      redirect: '/engrave/create',
      children: [
        {
          path: 'create',
          name: 'engravecreate',
          component: EngraveCreateView,
          redirect: '/engrave/create/1',
          children: [
            {
              path: '1', // 일반
              name: 'engravecreate1',
              component: EngraveCreate1,
              props: (route) => ({
                type: route.query.type,
              })
            },
            {
              path: '2', // 기독교
              name: 'engravecreate2',
              component: EngraveCreate2,
              props: (route) => ({
                type: route.query.type,
              })
            },
            {
              path: '3', // 불교
              name: 'engravecreate3',
              component: EngraveCreate3,
              props: (route) => ({
                type: route.query.type,
              })
            },
            {
              path: '4', // 천주교
              name: 'engravecreate4',
              component: EngraveCreate4,
              props: (route) => ({
                type: route.query.type,
              })
            },
            {
              path: '5', // SGI
              name: 'engravecreate5',
              component: EngraveCreate5,
              props: (route) => ({
                type: route.query.type,
              })
            },
            {
              path: '6', // 묘법
              name: 'engravecreate6',
              component: EngraveCreate6,
              props: (route) => ({
                type: route.query.type,
              })
            },
          ],
        },
        {
          path: 'result',
          name: 'engraveresult',
          component: EngraveResult,
          props: (route) => ({
            name: route.query.name,
            date1: route.query.date1,
            date2: route.query.date2
          })
        },
        {
          path: 'sample',
          name: 'engravesample',
          component: EngraveSample,
          props: (route) => ({
            name: route.query.name,
            date1: route.query.date1,
            date2: route.query.date2
          })
        }
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
