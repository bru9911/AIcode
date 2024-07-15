def calcular_imc(peso, altura):
    """
    Calcula o IMC (Índice de Massa Corporal).
    
    Args:
    peso (float): Peso em quilogramas.
    altura (float): Altura em metros.
    
    Returns:
    float: O valor do IMC.
    """
    return peso / (altura ** 2)

def classificar_imc(imc):
    """
    Classifica o IMC de acordo com as categorias padrão.
    
    Args:
    imc (float): O valor do IMC.
    
    Returns:
    str: A categoria do IMC.
    """
    if imc < 18.5:
        return "Abaixo do peso"
    elif 18.5 <= imc < 24.9:
        return "Peso normal"
    elif 25 <= imc < 29.9:
        return "Sobrepeso"
    else:
        return "Obesidade"

def main():
    try:
        peso = float(input("Digite o seu peso (em kg): "))
        altura = float(input("Digite a sua altura (em metros): "))
        
        imc = calcular_imc(peso, altura)
        categoria = classificar_imc(imc)
        
        print(f"Seu IMC é: {imc:.2f}")
        print(f"Categoria: {categoria}")
    except ValueError:
        print("Por favor, insira valores numéricos válidos.")

if __name__ == "__main__":
    main()
