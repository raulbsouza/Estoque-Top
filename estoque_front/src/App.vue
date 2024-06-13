<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/">Estoque Top</router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Marcas <i class="bi bi-chevron-down"></i></a>
              <ul class="dropdown-menu">
                <li><router-link to="/marca" class="dropdown-item">Listar Marcas</router-link></li>
                <li><router-link to="/marca/create" class="dropdown-item">Cadastrar Marca</router-link></li>
              </ul>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Produtos <i class="bi bi-chevron-down"></i></a>
              <ul class="dropdown-menu">
                <li><router-link to="/produtos" class="dropdown-item">Listar Produtos</router-link></li>
                <li><router-link to="/produto/create" class="dropdown-item">Cadastrar Produto</router-link></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container mt-4">
      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
</style>
