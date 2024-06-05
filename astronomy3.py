import ephem
import numpy as np
import matplotlib.pyplot as plt

# Função para calcular a distância entre dois corpos celestes
def calcular_distancia(corpo1, corpo2):
    corpo1.compute()
    corpo2.compute()
    distancia = ephem.separation(corpo1, corpo2)
    return distancia

# Função para obter as coordenadas de um corpo celeste
def obter_coordenadas(corpo):
    corpo.compute()
    return (np.degrees(corpo.ra), np.degrees(corpo.dec))

# Função principal
def main():
    # Corpos celestes
    nomes_corpos = ["Sol", "Mercúrio", "Vênus", "Terra", "Marte", "Júpiter", "Saturno", "Urano", "Netuno"]
    corpos_celestes = {nome: ephem.__getattribute__(nome)() for nome in nomes_corpos}

    # Datas de observação
    data_atual = ephem.now()
    data_futura = ephem.Date('2024/12/31')

    # Calcular coordenadas para a data atual e a data futura
    coordenadas_atual = {nome: obter_coordenadas(corpo) for nome, corpo in corpos_celestes.items()}
    coordenadas_futuras = {nome: obter_coordenadas(corpo) for nome, corpo in corpos_celestes.items()}

    # Calcular distâncias entre os corpos celestes para a data atual
    distancias = {(corpo1, corpo2): calcular_distancia(corpos_celestes[corpo1], corpos_celestes[corpo2])
                  for corpo1 in nomes_corpos for corpo2 in nomes_corpos if corpo1 != corpo2}

    # Exibir informações
    print("Coordenadas atuais dos corpos celestes:")
    for nome, coordenadas in coordenadas_atual.items():
        print(f"{nome}: RA={coordenadas[0]:.2f}°, DEC={coordenadas[1]:.2f}°")

    print("\nDistâncias entre os corpos celestes:")
    for corpos, distancia in distancias.items():
        print(f"{corpos[0]} - {corpos[1]}: {np.degrees(distancia):.2f}°")

    # Visualização dos dados
    plt.figure(figsize=(10, 6))
    for corpo, coordenadas in coordenadas_atual.items():
        plt.plot(coordenadas[0], coordenadas[1], 'o', label=corpo)

    plt.title('Posição dos Corpos Celestes')
    plt.xlabel('Ascensão Reta (graus)')
    plt.ylabel('Declinação (graus)')
    plt.legend()
    plt.grid(True)
    plt.show()

if __name__ == "__main__":
    main()
