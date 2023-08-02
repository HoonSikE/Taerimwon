import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EngraveView from '../components/EngraveView.vue'
import AboutView from '../views/AboutView.vue'
import EngraveCreateView from '../components/engrave/EngraveCreateView.vue'
import EngraveCreate1 from '../components/engrave/create/EngraveCreate1.vue'
import EngraveCreate2 from '../components/engrave/create/EngraveCreate2.vue'
import EngraveCreate3 from '../components/engrave/create/EngraveCreate3.vue'
import EngraveCreate4 from '../components/engrave/create/EngraveCreate4.vue'
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
              path: '1',
              name: 'engravecreate1',
              component: EngraveCreate1,
            },
            {
              path: '2',
              name: 'engravecreate2',
              component: EngraveCreate2,
            },
            {
              path: '3',
              name: 'engravecreate3',
              component: EngraveCreate3,
            },
            {
              path: '4',
              name: 'engravecreate4',
              component: EngraveCreate4,
            },
          ],
        },
        {
          path: 'result',
          name: 'engraveresult',
          component: EngraveResult,
        },
        {
          path: 'sample',
          name: 'engravesample',
          component: EngraveSample,
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
