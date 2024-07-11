pip install googletrans==4.0.0-rc1

from googletrans import Translator

def traduzir_texto(texto, src='auto', dest='en'):
    tradutor = Translator()
    traducao = tradutor.translate(texto, src=src, dest=dest)
    return traducao.text

def main():
    print("Bem-vindo ao Tradutor Simples!")
    texto = input("Digite o texto que você deseja traduzir: ")
    src = input("Digite o código da língua de origem (por exemplo, 'pt' para português, 'en' para inglês, ou deixe vazio para detecção automática): ")
    dest = input("Digite o código da língua de destino (por exemplo, 'en' para inglês, 'es' para espanhol): ")

    src = src if src else 'auto'
    
    traducao = traduzir_texto(texto, src=src, dest=dest)
    print(f"Tradução: {traducao}")

if __name__ == "__main__":
    main()
