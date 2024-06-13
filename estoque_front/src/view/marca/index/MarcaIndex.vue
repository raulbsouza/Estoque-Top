<template>
    <div class="container">
        <h1 class="text-center my-4">Marcas</h1>
        <div v-if="marcas && marcas.length > 0">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Id</th>
                        <th>Nome</th>
                        <th>Tipo</th>
                        <th class="text-center">Editar</th>
                        <th class="text-center">Deletar</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="marca in marcas" :key="marca.id">
                        <td>{{ marca.id }}</td>
                        <td>{{ marca.name }}</td>
                        <td>{{ marca.type }}</td>
                        <td class="text-center">
                            <router-link :to="{ name: 'marca-update', params: { id: marca.id } }" class="btn btn-primary btn-sm mr-2">
                                Editar
                            </router-link>
                        </td>
                        <td class="text-center">
                            <button @click="deleteMarca(marca.id)" class="btn btn-danger btn-sm">
                                Deletar
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div v-else>
            Carregando...
        </div>
    </div>
</template>

<script setup>
import axios from 'axios'
import baseUrl from '@/api_config.js'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'

const marcas = ref([])
const router = useRouter()

const getAll = () => {
    axios
        .get(baseUrl + '/api/marca')
        .then(response => {
            marcas.value = response.data
        })
        .catch(error => {
            console.error('Erro: ', error)
        })
}

const editMarca = (id) => {
    router.push({ name: 'marca-update', params: { id } })
}

const deleteMarca = (id) => {
    if (confirm("Tem certeza que deseja deletar esta marca?")) {
        axios.delete(baseUrl + `/api/marca/${id}`)
            .then(() => {
                marcas.value = marcas.value.filter(marca => marca.id !== id)
                alert("Marca deletada com sucesso!")
            })
            .catch(error => {
                console.error('Erro ao deletar marca: ', error)
                alert("Erro ao deletar marca!")
            })
    }
}

onMounted(() => {
    getAll()
})
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
