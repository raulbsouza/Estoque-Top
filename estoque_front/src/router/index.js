import { createRouter, createWebHistory } from 'vue-router';

import Home from '@/view/Home.vue'
import MarcaIndex from '@/view/marca/index/MarcaIndex.vue'
import MarcaCreate from '@/view/marca/create/MarcaCreate.vue'
import MarcaUpdate from '@/view/marca/update/MarcaUpdate.vue'
import ProdutoIndex from '@/view/produtos/ProdutoIndex.vue'
import ProdutoCreate from '@/view/produtos/create/ProdutoCreate.vue'
import ProdutoUpdate from '@/view/produtos/update/ProdutoUpdate.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/marca',
    name: 'marcas',
    component: MarcaIndex
  },
  {
    path: '/marca/create',
    name: 'marca-create',
    component: MarcaCreate
  },
  {
    path: '/marca/update/:id',
    name: 'marca-update',
    component: MarcaUpdate
  },
  {
    path: '/produtos',
    name: 'produtos',
    component: ProdutoIndex
  },
  {
    path: '/produto/create',
    name: 'produto-create',
    component: ProdutoCreate
  },
  {
    path: '/produto/update/:id',
    name: 'produto-update',
    component: ProdutoUpdate
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
