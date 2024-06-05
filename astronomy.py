class Astronomia:
    def __init__(self):
        self.constelacoes = ["Orion", "Ursa Maior", "Cassiopeia", "Centauro"]
        self.planetas = ["Mercúrio", "Vênus", "Terra", "Marte", "Júpiter", "Saturno", "Urano", "Netuno"]
        self.estrelas = ["Sirius", "Betelgeuse", "Rigel", "Aldebaran", "Antares"]

    def listar_constelacoes(self):
        print("Constelações:")
        for constelacao in self.constelacoes:
            print(constelacao)

    def listar_planetas(self):
        print("Planetas:")
        for planeta in self.planetas:
            print(planeta)

    def listar_estrelas(self):
        print("Estrelas:")
        for estrela in self.estrelas:
            print(estrela)

if __name__ == "__main__":
    astronomia = Astronomia()
    
    print("Bem-vindo ao programa de Astronomia!")
    print("Escolha uma opção:")
    print("1. Listar constelações")
    print("2. Listar planetas")
    print("3. Listar estrelas")
    opcao = input("Digite o número da opção desejada: ")

    if opcao == "1":
        astronomia.listar_constelacoes()
    elif opcao == "2":
        astronomia.listar_planetas()
    elif opcao == "3":
        astronomia.listar_estrelas()
    else:
        print("Opção inválida. Por favor, escolha uma opção válida.")
