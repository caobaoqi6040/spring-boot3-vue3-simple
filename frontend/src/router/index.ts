import {createRouter, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import GridView from "@/views/GridView.vue";
import FlexView from "@/views/FlexView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView,
        },
        {
            path: '/flex',
            name: 'flex',
            component: FlexView,
        },
        {
            path: '/grid',
            name: 'grid',
            component: GridView,
        },
        {
            path: '/about',
            name: 'about',
            // route level code-splitting
            // this generates a separate chunk (About.[hash].js) for this route
            // which is lazy-loaded when the route is visited.
            component: () => import('../views/AboutView.vue'),
        },
        {
            path: '/users',
            name: 'users',
            component: () => import('../views/UserListView.vue'),
        },
    ],
})

export default router
