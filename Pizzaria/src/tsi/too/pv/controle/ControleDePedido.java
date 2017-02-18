package tsi.too.pv.controle;

import java.util.ArrayList;

import tsi.too.pv.bd.AcompanhamentoBd;
import tsi.too.pv.bd.PedidoBd;
import tsi.too.pv.bd.PizzaPedidaBd;
import tsi.too.pv.entradas.EntradaESaida;
import tsi.too.pv.tipos.Acompanhamento;
import tsi.too.pv.tipos.Pedido;
import tsi.too.pv.tipos.PizzaPedida;
import tsi.too.pv.tipos.Pizzaria;

public class ControleDePedido {

	public ControleDePedido(){

	}// Fim do construtor()

	/** Fecha o pedido do usuário. 
	 * 
	 * @param listaAcompanhamentosEscolhidos é o <code>ArrayList</code> com todos os acompanhamentos escolhidos pelo usuário.
	 * @param listaPizzaPedida contém as informações da pizzaPedida.
	 * @param pedido é um objeto que contém as informações do pedido.
	 * 
	 * @return true se o pedido foi cadastrado corretamente e false caso contrário.
	 * */
	public boolean fechaPedido(ArrayList<Acompanhamento>listaAcompanhamentosEscolhidos, ArrayList<PizzaPedida> listaPizzaPedida, 
			Pedido pedido){

		// Verifica se a listaPizzasEscolhidas está vazia, caso esteja, return false.
		if(listaPizzaPedida.size() == 0){

			EntradaESaida.msgErro("Deve escolher uma pizza para finalizar o pedido", "Pedido");
			return false;

		}

		// Insere pedido.
		PedidoBd pedidoBd = new PedidoBd();
		if(pedidoBd.insereBd(pedido) == false){

			
			EntradaESaida.msgErro("Erro ao inserir pedido", "Pedido");

			pedidoBd.fechaConexao();
			return false;

		}

		// Pega o cdigo do último pedido.
		int codigoPedido = pedidoBd.ultimoCodigo();
		pedidoBd.fechaConexao();

		//Cadastrar PizzaPedida.
		PizzaPedidaBd pizzaPedidaBd = new PizzaPedidaBd();
		for(int contador = 0; contador < listaPizzaPedida.size(); contador++){

			listaPizzaPedida.get(contador).setNumeroPedido(codigoPedido);
			if (pizzaPedidaBd.insereBd(listaPizzaPedida.get(contador)) == false){

				
				EntradaESaida.msgErro("Erro ao inserir Pizza Pedida", "Pedido");

				pizzaPedidaBd.fechaConexao();
				return false;

			}


		}

		pizzaPedidaBd.fechaConexao();

		// Cadastra o acompanhamento.
		if(listaAcompanhamentosEscolhidos.size() != 0){

			AcompanhamentoBd acompanhamentosBd = new AcompanhamentoBd();

			for(int contador = 0; contador < listaAcompanhamentosEscolhidos.size(); contador++){
				listaAcompanhamentosEscolhidos.get(contador).setNumeroPedido(codigoPedido);
				if(acompanhamentosBd.insereBd(listaAcompanhamentosEscolhidos.get(contador)) == false){

					
					EntradaESaida.msgErro("Erro ao inserir Acompanhamento", "Pedido");

					acompanhamentosBd.fechaConexao();
					return false;

				}
			}

			acompanhamentosBd.fechaConexao();
		}


		EntradaESaida.msgInfo("Pedido Fechado", "Pedido");

		return true;
	}// fechaPedido()

	/** Pesquisa um pedido no banco de dados. 
	 * 
	 * @param numeroPedido é o número que será pesquisado no banco de dados.
	 * 
	 * @return Pedido se existir ou null caso contrário.
	 * */
	public Pedido conPedido(String numeroPedido){

		// Verifica se o numeroPedido é um número.
		int numero = EntradaESaida.stringToInt(numeroPedido);

		if(numero == -1)
			return null;

		PedidoBd pedidoBd = new PedidoBd();
		Pedido pedido = (Pedido) pedidoBd.obtemObjeto(numero);
		pedidoBd.fechaConexao();

		if(pedido == null){

			
			EntradaESaida.msgErro("Pedido não encontrado", "Consultar Pedido");

			return null;

		}

		return pedido;

	}// conPedido()

	/** Retorna uma lista com as pizzas pedidas referentes ao número do pedido. 
	 * 
	 * @param numero do pedido a ser pesquisado.
	 * 
	 * @return lista com as pizzas referentes ao número do pedido, null caso contrário.
	 * */	
	public ArrayList<PizzaPedida>listaPizzasPedidas(String numeroPedido){

		int numero = EntradaESaida.stringToInt(numeroPedido);

		if(numero == -1)
			return null;

		PizzaPedidaBd pizzaPedidaBd = new PizzaPedidaBd();
		ArrayList<PizzaPedida>listaPizzasPedidas = pizzaPedidaBd.obterPizzaPedidaBd(numero);
		pizzaPedidaBd.fechaConexao();

		if(listaPizzasPedidas == null)
			return null;

		return listaPizzasPedidas;

	}// listaPizzasPedidas()

	/** Retorna uma lista com os acompanhamentos pedidos referentes ao número do pedido. 
	 * 
	 * @param numero do pedido a ser pesquisado.
	 * 
	 * @return lista com os acompanhamentos referentes ao número do pedido, null caso contrário.
	 * */	
	public ArrayList<Acompanhamento>listaAcompanhamentos(String numeroPedido){

		int numero = EntradaESaida.stringToInt(numeroPedido);

		if(numero == -1)
			return null;

		AcompanhamentoBd acomapanhamentoBd = new AcompanhamentoBd();
		ArrayList<Acompanhamento>listaAcompanhamento = acomapanhamentoBd.obterListaAcompanhamentoBd(numero);
		acomapanhamentoBd.fechaConexao();

		if(listaAcompanhamento == null)
			return null;

		return listaAcompanhamento;

	}// listaPizzasPedidas()

	/** Fecha o pedido.
	 * 
	 *  @param numeroPedido código do pedido.
	 *  
	 *  @return true em caso de sucesso, false caso contrário.
	 *  
	 *  */
	public boolean encerraPedido(int pedido){

		PedidoBd pedidoBd = new PedidoBd();

		if(pedidoBd.encerraPedidoBd(pedido) == false){

			
			EntradaESaida.msgErro("Não foi possível fechar o pedido", "Encerrar Pedido");

			pedidoBd.fechaConexao();
			return false;

		}

		EntradaESaida.msgInfo("Pedido foi encerrado", "Encerrar Pedido");

		pedidoBd.fechaConexao();
		return true;

	}// fechaPedido()

	/** Exclui um pedido do sistema.
	 * 
	 * @param numero do pedido a ser excluído.
	 * 
	 * @return true em caso de sucesso, false caso contrário.
	 * */
	public boolean excPedido(int numeroPedido){

		PedidoBd pedidoBd = new PedidoBd();

		if(pedidoBd.removeBd(numeroPedido) == false){

			
			EntradaESaida.msgErro("Não foi possível excluir o pedido", "Excluir Pedido");

			pedidoBd.fechaConexao();
			return false;

		}

		EntradaESaida.msgInfo("Pedido Excluído", "Excluir Pedido");

		pedidoBd.fechaConexao();
		return true;
	}// excPedido()

	/** Pega todos pedidos do banco de dados. 
	 * 
	 * @return ArrayList dos pedidos, null caso contrário.
	 * */
	public ArrayList<Pizzaria> listarPedidos(){

		PedidoBd pedidoBd = new PedidoBd();
		ArrayList<Pizzaria>lista = pedidoBd.listarObjetos();

		if(lista == null){

			
			EntradaESaida.msgErro("Não foi possível listar Pedidos", "Listar Pedidos");

			pedidoBd.fechaConexao();
			return null;

		}


		pedidoBd.fechaConexao();
		return lista;

	}// listaPedidos()

	/** Pega todos pedidos do banco de dados de acordo com a data. 
	 * 
	 * @return ArrayList dos pedidos, null caso contrário.
	 * */
	public ArrayList<Pedido> listaPedidosData(String data){

		PedidoBd pedidoBd = new PedidoBd();
		ArrayList<Pedido>lista = pedidoBd.listaDataPedidos(data);

		if(lista == null){

			
			EntradaESaida.msgErro("Não foi possível listar Pedidos", "Listar Pedidos");

			pedidoBd.fechaConexao();
			return null;

		}


		pedidoBd.fechaConexao();
		return lista;

	}// listaPedidos()
	
}// Fim da class ControleDePedido()
