<template>
    <div class="container">
      <h1 class="text-center my-4">Produtos</h1>
      <div v-if="loading">
        Carregando...
      </div>
      <div v-else-if="produtos && produtos.length > 0">
        <table class="table table-striped table-bordered">
          <thead class="thead-dark">
            <tr>
              <th>Id</th>
              <th>Nome</th>
              <th>Marca</th>
              <th>Editar</th>
              <th>Deletar</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="produto in produtos" :key="produto.id">
              <td>{{ produto.id }}</td>
              <td>{{ produto.name }}</td>
              <td>{{ produto.marca.name }}</td>
              <td>
                <router-link :to="{ name: 'produto-update', params: { id: produto.id } }" class="btn btn-primary btn-sm mr-2">
                  Editar
                </router-link>
              </td>
              <td>
                <button @click="deleteProduto(produto.id)" class="btn btn-danger btn-sm">
                  Deletar
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>
        Nenhum produto encontrado.
      </div>
    </div>
  </template>
  
  <script setup>
  import axios from 'axios';
  import baseUrl from '@/api_config.js';
  import { onMounted, ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  const produtos = ref([]);
  const loading = ref(true);
  const router = useRouter();
  
  const getAllProdutos = () => {
    axios.get(`${baseUrl}/api/produto`)
      .then(response => {
        console.log('Resposta da API:', response.data);
        produtos.value = response.data._embedded ? response.data._embedded.produtos : [];
        loading.value = false;  
      })
      .catch(error => {
        console.error('Erro ao obter produtos: ', error);
        loading.value = false; 
      });
  };
  
  const deleteProduto = (id) => {
    if (confirm("Tem certeza que deseja deletar este produto?")) {
      axios.delete(`${baseUrl}/api/produto/${id}`)
        .then(() => {
          produtos.value = produtos.value.filter(produto => produto.id !== id);
          alert("Produto deletado com sucesso!");
        })
        .catch(error => {
          console.error('Erro ao deletar produto: ', error);
          alert("Erro ao deletar produto!");
        });
    }
  };
  
  onMounted(() => {
    getAllProdutos();
  });
  </script>
  
  <style scoped>
  .container {
    max-width: 800px;
    margin: auto;
  }
  
  .table {
    margin-top: 20px;
  }
  </style>
  