<template>
    <div class="container">
      <h1 class="text-center my-4">Editar Produto</h1>
      <template v-if="produto && marcas.length">
        <form @submit.prevent="updateProduto">
          <div class="form-group">
            <label for="name">Nome:</label>
            <input type="text" class="form-control" id="name" v-model="produto.name" required>
          </div>
          <div class="form-group">
            <label for="marca">Marca:</label>
            <select class="form-control" id="marca" v-model="produto.marca.id" required>
              <option v-for="marca in marcas" :key="marca.id" :value="marca.id">{{ marca.name }}</option>
            </select>
          </div>
          <button type="submit" class="btn btn-primary">Salvar</button>
        </form>
      </template>
      <div v-else>
        <p>Aguardando carregamento...</p>
      </div>
    </div>
  </template>
  
  <script setup>
  import axios from 'axios';
  import baseUrl from '@/api_config.js';
  import { ref, onMounted } from 'vue';
  import { useRouter, useRoute } from 'vue-router';
  
  const router = useRouter();
  const route = useRoute();
  const id = route.params.id;
  const produto = ref(null);
  const marcas = ref([]);
  
  const getProduto = () => {
    axios.get(`${baseUrl}/api/produto/${id}`)
      .then(response => {
        produto.value = response.data;
      })
      .catch(error => {
        console.error('Erro ao buscar produto: ', error);
      });
  };
  
  const getMarcas = () => {
    axios.get(`${baseUrl}/api/marca`)
      .then(response => {
        marcas.value = response.data; 
      })
      .catch(error => {
        console.error('Erro ao buscar marcas: ', error);
      });
  };
  
  const updateProduto = () => {
    axios.put(`${baseUrl}/api/produto/${id}`, produto.value)
      .then(response => {
        alert('Produto atualizado com sucesso!');
        router.push({ name: 'produtos' });
      })
      .catch(error => {
        console.error('Erro ao atualizar produto: ', error);
        alert('Erro ao atualizar produto!');
      });
  };
  
  onMounted(() => {
    getProduto();
    getMarcas();
  });
  </script>
  