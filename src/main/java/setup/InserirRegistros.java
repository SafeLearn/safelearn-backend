package setup;

import componentes.MemoriaRam;
import componentes.Sistema;
import componentes.UsoDisco;
import componentes.UsoProcessador;
import dao.LoginDao;
import dao.MaquinaDaoLocal;
import dao.MaquinaDaoServer;

public class InserirRegistros {
    private MaquinaDaoLocal maquinaDaoLocal;
    private MaquinaDaoServer maquinaDaoServer;
    private LoginDao loginDao;

    public InserirRegistros() {
        this.maquinaDaoLocal = new MaquinaDaoLocal();
        this.maquinaDaoServer = new MaquinaDaoServer();
        this.loginDao = new LoginDao();
        inserirRegistros();
    }

    public void inserirRegistros() {
        UsoProcessador processador = new UsoProcessador();
        Sistema sistema = new Sistema();
        MemoriaRam memoria = new MemoriaRam();
        UsoDisco disco = new UsoDisco();

        String nomeUsuario = new Logar().getNomeUsuario();
        Integer fkInstituicao = loginDao.getFkInstituicao(nomeUsuario);

        inserirDadosLocais(processador, memoria, disco, sistema, fkInstituicao);
        inserirDadosServidor(processador, memoria, disco, sistema, fkInstituicao);
    }

    private void inserirDadosLocais(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco, Sistema sistema, Integer fkInstituicao) {
        if (!maquinaDaoLocal.verificarRegistro(processador)) {
            maquinaDaoLocal.inserirDadosMaquina(processador, sistema, fkInstituicao);
            maquinaDaoLocal.inserirDadosComponente(processador, memoria, disco);
            System.out.println("Successfully registered locally!");
        } else {
            System.out.println("The machine is already registered locally.");
        }
    }

    private void inserirDadosServidor(UsoProcessador processador, MemoriaRam memoria, UsoDisco disco, Sistema sistema, Integer fkInstituicao) {
        if (!maquinaDaoServer.verificarRegistro(processador)) {
            maquinaDaoServer.inserirDadosMaquina(processador, sistema, fkInstituicao);
            maquinaDaoServer.inserirDadosComponente(processador, memoria, disco);
            System.out.println("Successfully registered on the server!");
        } else {
            System.out.println("The machine is already registered on the server.");
        }
    }
}
