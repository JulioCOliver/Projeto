package br.com.foursys.fourcamp.fourstore.communication;

import java.util.EnumSet;
import java.util.Scanner;

import br.com.fourcamp.fourstore.utils.Utils;
import br.com.foursys.fourcamp.fourstore.controller.ProductController;
import br.com.foursys.fourcamp.fourstore.enums.PaymentsEnum;
import br.com.foursys.fourcamp.fourstore.model.Product;
import br.com.foursys.fourcamp.fourstore.model.Sale;

public class MainMenu {
	static Scanner sc = new Scanner(System.in);

	public void MainMenu() {

		while (true) {
			System.out.println("\n ___________________________________" 
								+ "\n|     Bem Vindo ao FourMusic        |"
								+ "\n|___________________________________|" 
								+ "\n|1 - Cadastrar produto              |"
								+ "\n|2 - Atualizar produto              |" 
								+ "\n|3 - Venda                          |"
								+ "\n|4 - Listar produtos                |"
								+ "\n|5 - Pesquisar produto por sku      |"
								+ "\n|6 - Relat?rio de venda             |"
								+ "\n|--------------------------------   |"
								+ "\n| 0 - S A I R                       |" 
								+ "\n|___________________________________|"
								+ "\n"
								+ "\nInforme a opera??o desejada: ");

			int option = sc.nextInt();
			switch (option) {
			case 0:
				System.out.println("Volte sempre!");
				System.exit(0);
				break;
			case 1:
				registerProduct();
				break;
			case 2:
				Integer option2;
				System.out.println("    Digite a op??o desejada:" + "\n1 - Atualizar quantidade no estoque"
						+ "\n2 - Atualizar pre?o de venda do produto");
				option2 = sc.nextInt();
				if (option2 == 1) {
					addProduct();
				} else if (option2 == 2) {
					modifyPrice();
				} else {
					System.out.print("Op??o Inv?lida");
				}

				break;
			case 3:
				saleProduct();
				break;
			case 4:
				listAllProducts();
				break;
			case 5:
				searchBySku();
				break;
			case 6:
				report();
			}

		}
	}

	private void report() {
		System.out.println("Relat?rio de Vendas");

		ProductController productC = new ProductController();
		String return1 = productC.report();
		System.out.println(return1);

	}

	private void modifyPrice() {

		Scanner sc3 = new Scanner(System.in);
		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc3.next();
		System.out.println("Informe o novo pre?o de venda: ");
		Double salePrice = sc3.nextDouble();
		Object return1 = productCont.modifyPrice(sku, salePrice);
		System.out.println(return1);

	}

	private void addProduct() {
		Scanner sc3 = new Scanner(System.in);
		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc3.next();
		System.out.println("Quantidade de produtos adicionada ao estoque: ");
		Integer qtt = sc3.nextInt();
		Object return1 = productCont.addProduct(sku, qtt);
		System.out.println(return1);

	}

	private void saleProduct() {
		Integer option = 1;
		String cpf2 = null;
		Scanner sc2 = new Scanner(System.in);
		Double totalPrice = 0.0;
		ProductController productCont = new ProductController();
		Integer option2;
		
		while(option == 1) {
					
		System.out.println("Digite o SKU do produto: ");
		String sku = sc2.nextLine();
		System.out.println("Digite a quantidade do item: ");
		Integer qtt = sc2.nextInt();
		Product product = productCont.getProduct(sku);

		System.out.println("Adicionar CPF na nota?");
		System.out.println("1 - Sim" + " " + "2 - N?o");
		boolean return1 = true;
		option2 = sc.nextInt();
		if(option2 == 1) {
			System.out.println("Digite o CPF:");
		
		while(return1 == true) {			
			String cpf = sc.next();
			Utils.validateCPF(cpf);
			if(Utils.validateCPF(cpf) == false) {
				System.err.println("CPF inv?lido, digite da maneira correta! [xxx.xxx.xxx-xx]");
				return1 = true;
			}else {
				System.out.println("CPF v?lido!");
				return1 = false;
			}
		}
		}
		

		System.out.println("Qual a forma de pagamento?");
		for (PaymentsEnum p : EnumSet.allOf(PaymentsEnum.class)) {
			System.out.println(p.getKey());
		}
		String paymentType = sc.next();

		switch (paymentType) {
		case "1":
			getDebit();
			break;
		case "2":
			getCredit();
			totalPrice = product.getSalePrice() * qtt;
			totalPrice = totalPrice + (totalPrice * 0.5);
			System.out.println("O valor da compra com juros ?: " + totalPrice);
			break;
		case "3":
			getPix();
			totalPrice = product.getSalePrice() * qtt;
			totalPrice = totalPrice - (totalPrice * 0.5);
			System.out.println("O valor da compra com desconto ?: " + totalPrice);
			break;
		case "4":
			getMoney();
			totalPrice = product.getSalePrice() * qtt;
			totalPrice = totalPrice - (totalPrice * 0.5);
			System.out.println("O valor da compra com desconto ?: " + totalPrice);
			break;
		case "5":
			getTicket();
		default:
			break;
			
		}
		System.out.println("Deseja comprar mais algum produto?  1- Sim   2- N?o");
		option = sc.nextInt();
		sc.next();
	}if(option == 2) {
		System.out.println("Retorne para o menu!");
	}
}

	private static void searchBySku() {
		Scanner sc2 = new Scanner(System.in);

		ProductController productCont = new ProductController();
		System.out.println("Qual o sku do produto");
		String sku = sc2.nextLine();

		Object return1 = productCont.searchBySku(sku);
		System.out.println(return1);

	}

	private void listAllProducts() {
		ProductController productC = new ProductController();
		String return1 = productC.listAllProducts();
		System.out.println(return1);
	}

	private void registerProduct() {
		Integer option = 0;
		while (option != 2) {

			System.out.println("Insira O SKU do produto: ");
			String sku = sc.next();
			System.out.println("Insira A QUANTIDADE do produto: ");
			Integer qtt = sc.nextInt();
			System.out.println("Insira O PRE?O ORIGINAL do produto: ");
			Double originPrice = sc.nextDouble();
			System.out.println("Insira O PRE?O DE VENDA do produto: ");
			Double salePrice = sc.nextDouble();
			ProductController productC = new ProductController();
			String return1 = productC.registerProduct(sku, originPrice, salePrice, qtt);
			System.out.println(return1);
			System.out.println("Deseja cadastrar outro produto?   1- Sim   2- N?o");
			option = sc.nextInt();
		}
		if (option == 2) {
			System.out.println("");
		}
	}

	private static void getPix() {
		Utils utils = new Utils();
		Scanner sc = new Scanner(System.in);
		Integer option = sc.nextInt();	
		System.out.println("Escolha a sua chave pix  1-Celular  2- Cpf");
		if( option == 1) {
			System.out.println("Digite o n?mero de telefone");
		}else {
			System.out.println("Digite o seu CPF");
		}
		
	}

	private static void getDebit() {
		Utils utils = new Utils();
		Scanner sc = new Scanner(System.in);
		Integer option = 0;
		boolean return1 = true;
		while (return1 == true) {
			System.out.println("Digite o n?mero do cart?o!");
			String cardNumber = sc.next();
			utils.validateCard(cardNumber);
			if (utils.validateCard(cardNumber) == false) {
				System.err.println("Cart?o inv?lido, digite da maneira correta! [xxxx.xxxx.xxxx.xxxx.]");
				return1 = true;
			} else {
				System.out.println("Cart?o v?lido");
				return1 = false;
			}
		}

	}

	private static void getCredit() {
		Utils utils = new Utils();
		Scanner sc = new Scanner(System.in);
		Integer options = 0;
		boolean return1 = true;
		while (return1 == true) {
			System.out.println("Digite o n?mero do cart?o!");
			String cardNumber = sc.next();
			utils.validateCard(cardNumber);
			if (utils.validateCard(cardNumber) == false) {
				System.err.println("Cart?o inv?lido, digite da maneira correta! [xxxx.xxxx.xxxx.xxxx.]");
				return1 = true;
			} else {
				System.out.println("Cart?o v?lido");
				return1 = false;
			}

		}

	}

	private static void getMoney() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Ir? precisar de troco? 1- SIM  2-N?O ");
		Integer moneychange = sc.nextInt();
		if (moneychange == 1) {
			System.out.println("Quanto de troco?");
			moneychange = sc.nextInt();
			System.out.println("Confira o troco!");

		} else {
			System.out.println("Obrigado!");
		}

	}

	private static void getTicket() {
		Scanner sc = new Scanner(System.in);
		System.out.println("O seu boleto foi gerado   1- Pagar   2- Sair");
		Integer ticket = sc.nextInt();
		if (ticket == 1) {
			System.out.println("O seu boleto  foi pago!");
		} else {
			System.err.println("Voc? n?o realizou a compra!");
		}
	}
}
