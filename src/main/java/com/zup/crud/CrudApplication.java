package com.zup.crud;

import com.zup.crud.entities.User;
import com.zup.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class CrudApplication implements ApplicationRunner {

	@Autowired
	private  UserService userService;
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		String userOption;

		while (1 > 0){
			options();
			userOption = sc.next();
			executeOptions(userOption);
			System.out.println("Digite volte!");
			sc.next();
		}
	}

	public void options(){
		System.out.println("Bem vindo ao Desafio 2!");
		System.out.println("Lista de Opções:");
		System.out.println("1 - Cadastro");
		System.out.println("2 - Listar todos os usuarios");
		System.out.println("3 - Encontrar usuario");
		System.out.println("4 - Editar usuario");
		System.out.println("5 - Deletar usuario");
		System.out.println("6 - Sair");
	}

	public void executeOptions(String userOption){
		switch (userOption) {
			case "1":
				insertUser();
				break;
			case "2":
				listAllUsers();
				break;
			case "3":
				findUserByCPF();
				break;
			case "4":
				updateUserByCPF();
				break;
			case "5":
				deleteUserByCPF();
				break;
			case "6":
				System.out.println("Encerrado");
				System.exit(0);

			default:
				System.out.println("Opção inválida");
				break;
		}
	}

	private void insertUser() {
		sc.nextLine();
		System.out.println("Digite um nome:");
		String name = sc.nextLine();
		if(name.equals("")){
			System.out.println("Nome invalido!");
			return;
		}
		System.out.println("Digite uma idade:");
		int age = sc.nextInt();
		System.out.println("Digite seu CPF:");
		String cpf = sc.next();
		if(cpf.length()!=11 && cpf.length()!= 14) {
			System.out.println("CPF Invalido! Retornando ao começo");
			return;
		}
		boolean isEmailIdValid;
		System.out.println("Digite seu email:");
		String email = sc.next();
			if (email != null && email.length() > 0) {
				String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
				Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(email);
				if (matcher.matches()) {
					isEmailIdValid = true;
				}else{
					isEmailIdValid = false;
					System.out.println("Email nao valido!!!");
					return;
				}
			}
		System.out.println("Digite seu telefone");
		int phone = sc.nextInt();
		sc.nextLine();
		System.out.println("Digite seu Endereço:");
		String adress = sc.nextLine();
		System.out.println("Cadastro completo!");

		User user = new User(null, name, age , cpf, email, phone, adress );

		userService.insert(user);
	}

	private void listAllUsers(){
		List<User> listUsers = userService.findAll();

		System.out.println("Lista de todos os usuarios:");
		listUsers.forEach(user -> {
			System.out.println("Nome: " + user.getNome() + " | Idade: " + user.getIdade() + " | CPF: "
			+ user.getCPF() + " | Email: " + user.getEmail() + " | Telefone: " + user.getTelefone() + " | Endereço: " + user.getEndereco());
		});
	}

	public void findUserByCPF(){
		List<User> listUsers = userService.findAll();

		System.out.println("Digite um CPF para busca: ");
		String searchCPF = sc.next();

		listUsers.forEach(user -> {
			if (searchCPF.equals(user.getCPF())) {
				System.out.println("Nome: " + user.getNome() + " | Idade: " + user.getIdade() + " | CPF: "
						+ user.getCPF() + " | Email: " + user.getEmail() + " | Telefone: " + user.getTelefone() + " | Endereço: " + user.getEndereco());
			}else{
				System.out.println("CPF não encontrado!");
			}
		});

	}

	public void updateUserByCPF(){

		List<User> listUsers = userService.findAll();
		System.out.println("Digite um CPF para busca: ");
		String searchCPF = sc.next();

		listUsers.forEach(user -> {
			if (searchCPF.equals(user.getCPF())) {
				System.out.println("Alterar todos os valores?(s|n)");
				String option = sc.next();
				if(option.equals("s")){
					sc.nextLine();
					System.out.println("Digite um novo nome:");
					String name = sc.nextLine();
					if(name.equals("")){
						System.out.println("Nome invalido!");
						return;
					}
					System.out.println("Digite uma nova idade:");
					int age = sc.nextInt();
					boolean isEmailIdValid2;
					System.out.println("Digite um novo email:");
					String email = sc.next();
					if (email != null && email.length() > 0) {
						String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
						Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
						Matcher matcher = pattern.matcher(email);
						if (matcher.matches()) {
							isEmailIdValid2 = true;
						}else{
							isEmailIdValid2 = false;
							System.out.println("Email nao valido!!!");
							return;
						}
					}
					System.out.println("Digite um novo telefone");
					int phone = sc.nextInt();
					sc.nextLine();
					System.out.println("Digite um novo Endereço:");
					String adress = sc.nextLine();
					System.out.println("Atualização completa!");

					user.setNome(name);
					user.setIdade(age);
					user.setEmail(email);
					user.setTelefone(phone);
					user.setEndereco(adress);

					userService.update(user);

					System.out.println("Nome: " + user.getNome() + " | Idade: " + user.getIdade() + " | CPF: "
							+ user.getCPF() + " | Email: " + user.getEmail() + " | Telefone: " + user.getTelefone() + " | Endereço: " + user.getEndereco());
				} else if (option.equals("n")) {
					System.out.println("Qual campo quer alterar?");
					System.out.println("1 - Nome");
					System.out.println("2 - Idade");
					System.out.println("3 - Email");
					System.out.println("4 - Telefone");
					System.out.println("5 - Endereço");
					System.out.println("6 - Cancelar");
					String userOptionUpdate = sc.next();
					switch (userOptionUpdate) {
						case "1":
							System.out.println("Digite um novo nome:");
							sc.nextLine();
							String name = sc.nextLine();
							user.setNome(name);
							System.out.println("Alterado!");
							userService.update(user);
							break;
						case "2":
							System.out.println("Digite uma nova idade:");
							int age = sc.nextInt();
							user.setIdade(age);
							System.out.println("Alterado!");
							userService.update(user);
							break;
						case "3":
							System.out.println("Digite um novo email:");
							String email = sc.next();
							user.setEmail(email);
							System.out.println("Alterado!");
							userService.update(user);
							break;
						case "4":
							System.out.println("Digite um novo telefone");
							int phone = sc.nextInt();
							user.setTelefone(phone);
							System.out.println("Alterado!");
							userService.update(user);
							break;
						case "5":
							System.out.println("Digite um novo Endereço:");
							sc.nextLine();
							String adress = sc.nextLine();
							user.setEndereco(adress);
							System.out.println("Alterado!");
							userService.update(user);
							break;
						case "6":
							System.out.println("Saindo...");
							break;

						default:
							System.out.println("Opção inválida");
							break;
					}
				}
			;}else{
				System.out.println("CPF não encontrado!");
			}
		});
	}

	public void deleteUserByCPF(){
		List<User> listUsers = userService.findAll();

		System.out.println("Digite um CPF para busca: ");
		String searchCPF = sc.next();

		listUsers.forEach(user -> {
			if (searchCPF.equals(user.getCPF())) {
				System.out.println("Usuario com o CPF "+ user.getCPF() + " deletado");
				userService.delete(user.getId());
			}else{
				System.out.println("CPF não encontrado!");
			}
		});
	;}
}
