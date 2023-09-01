import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '../views/HomeView.vue'
import EngraveView from '../views/EngraveView.vue'
import AdminView from '../views/AdminView.vue'
import AboutView from '../views/AboutView.vue'

// 각인 위패
import EngraveCreateView from '../components/engrave/EngraveCreateView.vue'
import EngraveCreateDetail from '../components/engrave/create/EngraveCreateDetail.vue'
import TabletCreateView from '../components/tablet/TabletCreateView.vue'
import ResultView from '../components/result/ResultView.vue'

// 관리자 페이지
import Login from '../components/admins/LoginView.vue'
import AdminHome from '../components/admins/AdminHomeView.vue'
import AdminSignUp from '../components/admins/adminHome/AdminSignUpView.vue'
import AdminPhoneList from '../components/admins/adminHome/AdminPhoneList.vue'
import AdminOrderList from '../components/admins/adminHome/AdminOrderListView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: HomeView,
      // redirect: '/engrave/engraveCreate',
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
          redirect: '/engrave/engraveCreate/engraveDetail',
          children: [
            {
              path: 'engraveDetail',
              name: 'engraveDetail',
              component: EngraveCreateDetail,
              children: [
                {
                  path: 'tabletCreate',
                  name: 'tabletCreateView',
                  component: TabletCreateView,
                },
              ],
            },
          ],
        },
        {
          path: 'result',
          name: 'result',
          component: ResultView,
        },
      ],
    },
    {
      path: '/admin',
      name: 'admin',
      component: AdminView,
      redirect: '/admin/login',
      children: [
        {
          path: 'login',
          name: 'login',
          component: Login,
        },
        {
          path: 'adminHome',
          name: 'adminHome',
          component: AdminHome,
          redirect: '/admin/adminHome/adminSignUp',
          children: [
            {
              path: 'adminSignUp',
              name: 'adminSignUp',
              component: AdminSignUp,
            },
            {
              path: 'adminPhoneList',
              name: 'adminPhoneList',
              component: AdminPhoneList,
            },
            {
              path: 'adminOrderList',
              name: 'adminOrderList',
              component: AdminOrderList,
            },
          ]
        },
      ],
    },
    {
      path: '/about',
      name: 'about',
      component: AboutView
    },
  ]
})

export default router
