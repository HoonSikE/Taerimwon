import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EngraveView from '../components/EngraveView.vue'
import AboutView from '../views/AboutView.vue'
import EngraveCreate from '../components/engrave/EngraveCreate.vue'
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
          component: EngraveCreate,
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
