# Gerar notas aleatórias para 100 alunos (entre 0 e 10)
import random

notas = [random.uniform(0, 10) for _ in range(100)]

# Calcular a média das notas
media = sum(notas) / len(notas)

print(f"A média das notas dos 100 alunos é: {media:.2f}")
