  document.addEventListener("DOMContentLoaded", async () => {
      const selectEstilo = document.getElementById("estilo");

      try {
          const response = await fetch('js/estilos.json');

          if (!response.ok) {
              throw new Error("Não foi possível carregar os estilos musicais.");
          }

          const estilos = await response.json();

          selectEstilo.innerHTML = '<option value="">Selecione um estilo</option>';

          estilos.forEach(estilo => {
              const option = document.createElement("option");
              option.value = estilo.nome;
              option.textContent = estilo.nome;
              selectEstilo.appendChild(option);
          });

      } catch (error) {
          console.error("Erro ao buscar estilos:", error);
          selectEstilo.innerHTML = '<option value="">Erro ao carregar estilos</option>';
      }
  });

  document.getElementById('form-musica').addEventListener('submit', async function(e) {
            e.preventDefault();

            const url = 'http://localhost:8080/mysong';
            const estilo = document.getElementById('estilo').value;
            const nome = document.getElementById('nome').value;
            const artista = document.getElementById('artista').value;
            const audioFile = document.getElementById('audio').files[0];

            const formData = new FormData();
            formData.append('estilo', estilo);
            formData.append('titulo', nome);
            formData.append('artista', artista);
            formData.append('audio', audioFile);

            try {
                const response = await fetch(url + '/add-music', {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    document.getElementById('mensagem').style.color = 'green';
                    document.getElementById('mensagem').textContent = 'Música cadastrada com sucesso!';
                    document.getElementById('form-musica').reset();
                } else {
                    const erro = await response.text();
                    document.getElementById('mensagem').style.color = 'red';
                    document.getElementById('mensagem').textContent = 'Erro: ' + erro;
                }
            } catch (err) {
                document.getElementById('mensagem').style.color = 'red';
                document.getElementById('mensagem').textContent = 'Erro de conexão.';
            }
        });