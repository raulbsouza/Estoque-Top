<template>
    <div class="container">
      <h2 class="my-4 text-center">Cadastrar Nova Marca</h2>
      <hr>
      <form v-on:submit.prevent="create" class="form-inline">
        <div class="form-group mb-2">
          <label for="name" class="sr-only">Nome</label>
          <input
            type="text"
            id="name"
            class="form-control"
            v-model="name"
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
            v-model="type"
            placeholder="Tipo da Marca"
            required
          />
        </div>
        <button type="submit" class="btn btn-primary mb-2">Cadastrar</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from "vue"
  import axios from "axios";
  import baseUrl from '@/api_config.js'
  import { useRouter } from 'vue-router'
  
  const name = ref("")
  const type = ref("")
  
  const router = useRouter()
  
  const create = () => {
    axios.post(baseUrl + '/api/marca', 
      {
        name: name.value,
        type: type.value
      }
    ).then((response) => {
      alert("Marca Cadastrada com Sucesso!")
      router.push("/marca")
    }).catch((error => {
      alert("Erro: " + error)
    }))
  }
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
  