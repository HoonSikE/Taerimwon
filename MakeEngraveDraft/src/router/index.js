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
import AdminOrderList from '../components/admins/adminHome/AdminOrderListView.vue'

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
