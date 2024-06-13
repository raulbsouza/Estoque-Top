<template>
    <div class="container">
      <h2 class="my-4 text-center">Atualizar Marca</h2>
      <hr>
      <form v-on:submit.prevent="update" class="form-inline">
        <div class="form-group mb-2">
          <label for="name" class="sr-only">Nome</label>
          <input
            type="text"
            id="name"
            class="form-control"
            v-model="marca.name"
            placeholder="Nome da Marca"
            required
          />
        </div>
        <div class="form-group mb-2">
          <label for="type" class="sr-only">Tipo</label>
          <input
            type="text"
            id="type"
            class="form-control"
            v-model="marca.type"
            placeholder="Tipo da Marca"
            required
          />
        </div>
        <button type="submit" class="btn btn-primary mb-2">Atualizar</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { onMounted, reactive } from "vue"
  import axios from "axios";
  import baseUrl from '@/api_config.js'
  import { useRouter, useRoute } from 'vue-router'
  
  const route = useRoute()
  
  const marca = reactive({
    id: null,
    name: '',
    type: ''
  })
  
  const router = useRouter()
  
  const update = () => {
    axios.put(`${baseUrl}/api/marca/${marca.id}`, marca)
      .then((response) => {
        alert("Marca Atualizada com Sucesso!")
        router.push("/marca")
      }).catch((error => {
        console.error(error)
        alert("Erro: " + error)
      }))
  }
  
  onMounted(() => {
    axios.get(`${baseUrl}/api/marca/${route.params.id}`)
      .then((response) => {
        marca.id = response.data.id
        marca.name = response.data.name
        marca.type = response.data.type
      })
      .catch((error) => {
        console.error(error)
        alert("Erro ao carregar a marca!")
      })
  })
  </script>
  
  <style scoped>
  .container {
    max-width: 600px;
    margin: auto;
  }
  
  .form-inline {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
  }
  
  .form-group {
    width: 100%;
    margin-bottom: 1rem;
  }
  
  .form-control {
    width: 100%;
  }
  
  .btn {
    align-self: flex-start;
  }
  </style>
  