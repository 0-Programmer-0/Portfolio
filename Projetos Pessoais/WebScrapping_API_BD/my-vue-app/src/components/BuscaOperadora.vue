<template>
  <div>
    <input v-model="nome" placeholder="Digite o nome da operadora" />
    <button @click="buscar">Buscar</button>
    <ul>
      <li v-for="operadora in operadoras" :key="operadora.registro_ans">
        {{ operadora.Nome_Fantasia }} - {{ operadora.Cidade }} ({{ operadora.UF }})
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      nome: "",
      operadoras: []
    };
  },
  methods: {
    async buscar() {
      try {
        const resposta = await axios.get(`http://127.0.0.1:8000/buscar?nome=${this.nome.trim()}`);
        console.log(resposta.data);
        this.operadoras = resposta.data;
      }
      catch (erro) {
        console.error('Erro ao buscar operadoras: ', erro);
      }
    }
  }
};
</script>
