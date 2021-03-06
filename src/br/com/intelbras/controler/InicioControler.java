/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.intelbras.controler;

import br.com.intelbras.model.Funcionario;
import br.com.intelbras.view.ClienteView;
import br.com.intelbras.view.FuncionarioView;
import br.com.intelbras.view.InicioView;
import br.com.intelbras.view.LoginManagerView;
import br.com.intelbras.view.LoginView;
import br.com.intelbras.view.PontosView;
import br.com.intelbras.view.ProdutoView;
import br.com.intelbras.view.VendasGerenteView;
import br.com.intelbras.view.VendasView;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author WesleyReis
 */
public class InicioControler {

    private InicioView tela;
    private HashMap<String, Object> mapa;

    public InicioControler(HashMap<String, Object> mapa) {
        this.mapa = mapa;

        tela = ((InicioView) mapa.get("tela"));
        tela.setExtendedState(tela.MAXIMIZED_BOTH);

        ImageIcon img = new javax.swing.ImageIcon("C:\\Users\\Public\\Pictures\\telaFundo.png");
        img.setImage(img.getImage().getScaledInstance(tela.getWidth(), tela.getHeight(), 100));
        ((JLabel) mapa.get("lbl_fundo")).setIcon(img);

        tela.repaint();

    }

    public void arrastarBotao(JButton botao, MouseEvent evt) {
        botao.setLocation(botao.getLocation().x + evt.getX() - (botao.getSize().width / 2), botao.getLocation().y + evt.getY() - (botao.getSize().height / 2));
    }

    public void setPosicao() {
        try {
            Arquivo arquivo = new Arquivo();

            ArrayList<Point> array = arquivo.leCoordenadaBotoes();

            if (array != null) {
                ((JButton) mapa.get("btn_cliente")).setLocation(array.get(0));
                ((JButton) mapa.get("btn_funcionario")).setLocation(array.get(1));
                ((JButton) mapa.get("btn_vendas")).setLocation(array.get(2));
                ((JButton) mapa.get("btn_pontos")).setLocation(array.get(3));
                ((JButton) mapa.get("btn_produto")).setLocation(array.get(4));
                ((JButton) mapa.get("btn_login")).setLocation(array.get(5));
                ((JButton) mapa.get("btn_historico")).setLocation(array.get(6));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Deu merda");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Deu merda");
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Deu merda");
            System.out.println(ex);
        }
    }

    public void guardaPosicao() {
        try {
            Arquivo arquivo = new Arquivo();

            ArrayList<String> lista = new ArrayList<>();
            String cliente = ((JButton) mapa.get("btn_cliente")).getLocation().x + "-" + ((JButton) mapa.get("btn_cliente")).getLocation().y;
            String funcionario = ((JButton) mapa.get("btn_funcionario")).getLocation().x + "-" + ((JButton) mapa.get("btn_funcionario")).getLocation().y;
            String vendas = ((JButton) mapa.get("btn_vendas")).getLocation().x + "-" + ((JButton) mapa.get("btn_vendas")).getLocation().y;
            String ponto = ((JButton) mapa.get("btn_pontos")).getLocation().x + "-" + ((JButton) mapa.get("btn_pontos")).getLocation().y;
            String produto = ((JButton) mapa.get("btn_produto")).getLocation().x + "-" + ((JButton) mapa.get("btn_produto")).getLocation().y;
            String login = ((JButton) mapa.get("btn_login")).getLocation().x + "-" + ((JButton) mapa.get("btn_login")).getLocation().y;
            String historico = ((JButton) mapa.get("btn_historico")).getLocation().x + "-" + ((JButton) mapa.get("btn_historico")).getLocation().y;

            lista.add(cliente);
            lista.add(funcionario);
            lista.add(vendas);
            lista.add(ponto);
            lista.add(produto);
            lista.add(login);
            lista.add(historico);

            arquivo.salvaCoordenadaBotoes(lista);

        } catch (FileNotFoundException ex) {
            System.out.println("Deu merda");
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println("Deu merda");
            System.out.println(ex);
        }
    }

    public void mudarPlanoFundo(JLabel label) {
        alterarImagem(label);
    }

    public void logout(InicioView tela) {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);
        tela.dispose();

    }
    //============================== Abrir Views
    //============================== 

    public void abrirCliente(InicioView tela) {
        if (Funcionario.nivelAcessoLogado != 2) {
            ClienteView cliente = new ClienteView();
            cliente.setVisible(true);
            cliente.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirFuncionario(InicioView tela) {
        if (Funcionario.nivelAcessoLogado == 2) {
            FuncionarioView funcionario = new FuncionarioView();
            funcionario.setVisible(true);
            funcionario.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirProduto(InicioView tela) {
        if (Funcionario.nivelAcessoLogado == 1) {
            ProdutoView produto = new ProdutoView();
            produto.setVisible(true);
            produto.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirPonto(InicioView tela) {
        if (Funcionario.nivelAcessoLogado == 1) {
            PontosView ponto = new PontosView();
            ponto.setVisible(true);
            ponto.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirVenda(InicioView tela) {
        if (Funcionario.nivelAcessoLogado != 2) {
            VendasView venda = new VendasView();
            venda.setVisible(true);
            venda.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirLogin(InicioView tela) {
        if (Funcionario.nivelAcessoLogado == 2) {
            LoginManagerView loginManager = new LoginManagerView();
            loginManager.setVisible(true);
            loginManager.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirHistorico(InicioView tela) {
        if (Funcionario.nivelAcessoLogado == 1) {
            VendasGerenteView vendaG = new VendasGerenteView();
            vendaG.setVisible(true);
            vendaG.setLocationRelativeTo(tela);
        } else {
            JOptionPane.showMessageDialog(tela, "Você não tem permição para acessar esta área!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    //============================== Metodos privados
    //============================== 
    // Metodo para alterar a imagem do label de fundo
    private void alterarImagem(JLabel label) {

        // seta imagens
        try {
            // TODO add your handling code here:    
            JFileChooser chooser = new JFileChooser();//cria uma instancia do JFileChooser
            //filtro para as imagend
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & GIF & PNG Images", "jpg", "gif", "png");
            //altera o filtro
            chooser.setFileFilter(filtro);

            String local = "";
            int returnVal = chooser.showOpenDialog(label);//abre JFileChooser
            if (returnVal == JFileChooser.APPROVE_OPTION) {//verifica se clicou em ok
                local = (chooser.getSelectedFile().getAbsolutePath());//local recebe a localização da imagem           
            }

            File origem = new File(local);
            File destino = new File("C:\\Users\\Public\\Pictures\\telaFundo.png");
            copiarArquivo(origem, destino);

            ImageIcon img = new javax.swing.ImageIcon("C:\\Users\\Public\\Pictures\\telaFundo.png");
            img.setImage(img.getImage().getScaledInstance(tela.getWidth(), tela.getHeight(), 100));

            label.setIcon(img);
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo nao encontrado");
        } catch (IOException ex) {
            System.out.println("Erro ao importar");
        }
    }

    // Metodo para compiar um arquivo para outro diretorio
    private void copiarArquivo(File source, File destination) throws IOException {
        if (destination.exists()) {
            destination.delete();
        }
        FileChannel sourceChannel = null;
        FileChannel destinationChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();
            destinationChannel = new FileOutputStream(destination).getChannel();
            sourceChannel.transferTo(0, sourceChannel.size(),
                    destinationChannel);
        } finally {
            if (sourceChannel != null && sourceChannel.isOpen()) {
                sourceChannel.close();
            }
            if (destinationChannel != null && destinationChannel.isOpen()) {
                destinationChannel.close();
            }
        }
    }

}
