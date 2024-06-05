from astropy import constants as const
from astropy import units as u
from qiskit import QuantumCircuit, Aer, execute

def calcular_massa_exoplaneta(raio, densidade):
    # Calculando a massa do exoplaneta
    massa = (4/3) * const.pi * raio**3 * densidade
    return massa.to(u.kg)

def criar_circuito_quantico():
    # Criando um circuito quântico simples com a operação de Hadamard
    qc = QuantumCircuit(1)
    qc.h(0)  # Aplicando a operação de Hadamard
    return qc

if __name__ == "__main__":
    # Astronomia: Calculando a massa de um exoplaneta
    raio_exoplaneta = 1.5 * const.R_earth  # Raio do exoplaneta em relação ao raio da Terra
    densidade_exoplaneta = 5 * u.g / u.cm**3  # Densidade do exoplaneta
    massa_exoplaneta = calcular_massa_exoplaneta(raio_exoplaneta, densidade_exoplaneta)
    print(f"A massa do exoplaneta é: {massa_exoplaneta:.2e}")

    # Física Quântica: Criando um circuito quântico com a operação de Hadamard
    circuito_quantico = criar_circuito_quantico()
    print("Circuito quântico criado:")
    print(circuito_quantico)

    # Simulando o circuito quântico
    simulator = Aer.get_backend('statevector_simulator')
    job = execute(circuito_quantico, simulator)
    resultado = job.result().get_statevector(circuito_quantico, decimals=3)
    print("Resultado da simulação do circuito quântico:")
    print(resultado)
