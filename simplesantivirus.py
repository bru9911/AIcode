import os
import hashlib

# Lista de hashes de arquivos conhecidos como maliciosos
hashes_maliciosos = [
    "5d41402abc4b2a76b9719d911017c592",  # Exemplo de hash
    "7d793037a0760186574b0282f2f435e7",  # Exemplo de hash
    # Adicione mais hashes conforme necessário
]

def calcular_hash_arquivo(caminho_arquivo):
    hash_md5 = hashlib.md5()
    with open(caminho_arquivo, "rb") as f:
        for chunk in iter(lambda: f.read(4096), b""):
            hash_md5.update(chunk)
    return hash_md5.hexdigest()

def verificar_arquivos(diretorio):
    arquivos_infectados = []
    for root, _, files in os.walk(diretorio):
        for file in files:
            caminho_arquivo = os.path.join(root, file)
            hash_arquivo = calcular_hash_arquivo(caminho_arquivo)
            if hash_arquivo in hashes_maliciosos:
                arquivos_infectados.append(caminho_arquivo)
    return arquivos_infectados

def main():
    diretorio = input("Digite o caminho do diretório para verificação: ")
    arquivos_infectados = verificar_arquivos(diretorio)

    if arquivos_infectados:
        print("Arquivos infectados encontrados:")
        for arquivo in arquivos_infectados:
            print(arquivo)
    else:
        print("Nenhum arquivo infectado encontrado.")

if __name__ == "__main__":
    main()
