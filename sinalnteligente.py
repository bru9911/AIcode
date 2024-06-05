import numpy as np
import matplotlib.pyplot as plt

# Parâmetros do sinal
frequencia_central = 1420e6  # Frequência central em Hz (1.42 GHz, frequência do hidrogênio neutro)
largura_banda = 1e6  # Largura de banda em Hz
amostras_por_segundo = 1e6  # Taxa de amostragem em amostras por segundo
tempo_total = 10  # Tempo total de observação em segundos
amplitude_sinal = 1.0  # Amplitude do sinal

# Gerar sinal simulado
t = np.linspace(0, tempo_total, int(amostras_por_segundo * tempo_total), endpoint=False)
sinal = np.sin(2 * np.pi * frequencia_central * t) * amplitude_sinal

# Inserir sinal de pulso em um intervalo aleatório
inicio_pulso = np.random.uniform(0, tempo_total - 0.5)
fim_pulso = inicio_pulso + 0.5
sinal[int(inicio_pulso * amostras_por_segundo):int(fim_pulso * amostras_por_segundo)] += 10.0

# Plotar sinal
plt.figure(figsize=(10, 4))
plt.plot(t, sinal)
plt.title('Sinal Observado')
plt.xlabel('Tempo (s)')
plt.ylabel('Amplitude')
plt.grid(True)
plt.show()

# Detectar pulso no sinal
threshold = 5.0
pulsos_detectados = []
for i in range(1, len(sinal) - 1):
    if sinal[i] > threshold and sinal[i] > sinal[i-1] and sinal[i] > sinal[i+1]:
        pulsos_detectados.append(t[i])

# Exibir resultados
if pulsos_detectados:
    print("Pulso detectado em segundos:")
    for pulso in pulsos_detectados:
        print(pulso)
else:
    print("Nenhum pulso detectado.")
