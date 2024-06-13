<template>
    <div>
      <div>
        <div class="text-center mt-5">
          <h1>Estoque Top</h1>
        </div>
  
        <div class="container mt-5">
          <div class="row justify-content-center">
            <div class="col-md-6 text-center">
              <router-link to="/marca" class="btn btn-lg btn-pink w-75 mb-3">Ver Marcas</router-link>
            </div>
            <div class="col-md-6 text-center">
              <router-link to="/produtos" class="btn btn-lg btn-pink w-75 mb-3">Ver Produtos</router-link>
            </div>
          </div>
        </div>
      </div>
  
      <footer class="text-center fixed-bottom bg-light py-3">
        <p class="text-muted">Desenvolvido por Raul e Lucas</p>
      </footer>
    </div>
  </template>
  
  <script setup>
  </script>
  
  <style scoped>
  .btn-pink {
    background-color: #ff4081;
    border-color: #ff4081;
  }
  .btn-pink:hover {
    background-color: #ff80ab;
    border-color: #ff80ab;
  }
  </style>
  