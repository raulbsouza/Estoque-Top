<template>
    <div class="container">
      <h1 class="text-center my-4">Criar Produto</h1>
      <template v-if="marcas.length">
        <form @submit.prevent="createProduto">
          <div class="form-group">
            <label for="name">Nome:</label>
            <input type="text" class="form-control" id="name" v-model="produto.name" required>
          </div>
          <div class="form-group">
            <label for="marca">Marca:</label>
            <select class="form-control" id="marca" v-model="produto.marcaId" required>
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
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  const produto = ref({
    name: '',
    marcaId: null 
  });
  const marcas = ref([]);
  
  const getMarcas = () => {
    axios.get(`${baseUrl}/api/marca`)
      .then(response => {
        marcas.value = response.data;
      })
      .catch(error => {
        console.error('Erro ao buscar marcas: ', error);
      });
  };
  
  const createProduto = () => {
    axios.post(`${baseUrl}/api/produto`, {
      name: produto.value.name,
      marca: { id: produto.value.marcaId } 
    })
      .then(response => {
        alert('Produto criado com sucesso!');
        router.push({ name: 'produtos' });
      })
      .catch(error => {
        console.error('Erro ao criar produto: ', error);
        alert('Erro ao criar produto!');
      });
  };
  
  onMounted(() => {
    getMarcas();
  });
  </script>
  