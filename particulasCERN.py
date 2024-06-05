from particle import Particle

def listar_particulas_cern():
    # Lista de identificadores de partículas usadas no CERN
    particulas_cern = ["proton", "neutron", "photon", "electron", "muon", "pion", "kaon", "lambda_baryon"]
    
    print("Partículas usadas no laboratório CERN:")
    for nome_particula in particulas_cern:
        particula = Particle.find(name=nome_particula)
        print(f"{particula}: Massa = {particula.mass} GeV/c^2, Carga elétrica = {particula.charge} e, Spin = {particula.spin}")

if __name__ == "__main__":
    listar_particulas_cern()
