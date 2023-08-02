import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import EngraveView from '../components/engrave/EngraveView.vue'
import AboutView from '../views/AboutView.vue'
import EngraveResult from '../components/engrave/Engraveresult.vue'

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
      children: [
        {
          path: 'result',
          name: 'engraveresult',
          component: EngraveResult
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
