# CAEst
Controle de Acesso ao Estacionamento. Repositório para facilitar transição de dados e apresentações tanto quanto desenvolvimento.

CASO PRECISE INICIAR O BANCO:
CREATE TABLE Motorista(
	id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(45) NOT NULL,
    cpf CHAR(11) NOT NULL UNIQUE,
    matricula CHAR(9),
    vinculo ENUM('aluno', 'servidor', 'terceirizado') NOT NULL);
    
CREATE TABLE Veiculo(
	id INT AUTO_INCREMENT PRIMARY KEY,
    placa CHAR(7) NOT NULL UNIQUE,
    modelo VARCHAR(45) NOT NULL,
    cor VARCHAR(45),
    idMotorista INT NOT NULL,
    FOREIGN KEY (idMotorista) REFERENCES Motorista(id));
    
CREATE TABLE LiberacaoManual(
	id INT AUTO_INCREMENT PRIMARY KEY,
    docMotorista VARCHAR(11) NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    dataHora DATETIME NOT NULL,
    idVeiculo INT NOT NULL,
    FOREIGN KEY (idVeiculo) REFERENCES Veiculo(id));
