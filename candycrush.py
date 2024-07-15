pip install pygame
#Abaixo está um exemplo de código para um jogo de combinação de três peças:

python
Copiar código
import pygame
import random

# Inicializa o pygame
pygame.init()

# Configurações da tela
LARGURA_TELA = 600
ALTURA_TELA = 600
TAMANHO_BLOCO = 40
MARGEM = 5

# Cores
CORES = [
    (255, 0, 0),  # Vermelho
    (0, 255, 0),  # Verde
    (0, 0, 255),  # Azul
    (255, 255, 0),  # Amarelo
    (255, 165, 0),  # Laranja
    (128, 0, 128),  # Roxo
]

# Inicializa a tela
tela = pygame.display.set_mode((LARGURA_TELA, ALTURA_TELA))
pygame.display.set_caption("Jogo de Combinação de Três Peças")

# Função para desenhar o tabuleiro
def desenhar_tabuleiro(tabuleiro):
    for i in range(len(tabuleiro)):
        for j in range(len(tabuleiro[i])):
            cor = CORES[tabuleiro[i][j]]
            pygame.draw.rect(tela, cor, (j * (TAMANHO_BLOCO + MARGEM), i * (TAMANHO_BLOCO + MARGEM), TAMANHO_BLOCO, TAMANHO_BLOCO))

# Função para criar um tabuleiro inicial
def criar_tabuleiro(linhas, colunas):
    tabuleiro = []
    for i in range(linhas):
        linha = []
        for j in range(colunas):
            linha.append(random.randint(0, len(CORES) - 1))
        tabuleiro.append(linha)
    return tabuleiro

# Função principal do jogo
def jogo():
    linhas = ALTURA_TELA // (TAMANHO_BLOCO + MARGEM)
    colunas = LARGURA_TELA // (TAMANHO_BLOCO + MARGEM)
    tabuleiro = criar_tabuleiro(linhas, colunas)

    # Loop principal do jogo
    rodando = True
    while rodando:
        for evento in pygame.event.get():
            if evento.type == pygame.QUIT:
                rodando = False

        # Desenha o tabuleiro
        tela.fill((0, 0, 0))
        desenhar_tabuleiro(tabuleiro)
        pygame.display.flip()

    pygame.quit()

# Executa o jogo
if __name__ == "__main__":
    jogo()
Este código cria um tabuleiro simples e o desenha na tela. As peças são quadrados coloridos que são desenhados aleatoriamente. O jogo atualmente não possui lógica de movimento ou verificação de combinações, mas serve como base para expandir e adicionar funcionalidades.

Para tornar este jogo mais semelhante ao Candy Crush, você precisaria adicionar:

Lógica para detectar e remover combinações de três ou mais peças.
Lógica para mover peças.
Animações e efeitos visuais.
Pontuação e progressão de níveis.
Se você quiser mais detalhes sobre como implementar essas funcionalidades, posso ajudar com partes específicas.
