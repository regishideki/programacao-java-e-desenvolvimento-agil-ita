import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class CaixaEletronicoProblemaNoHardwareTest {
    private CaixaEletronico caixa;
    private HardwareMock hardwareMock;
    private ServicoRemotoMock servicoRemotoMock;
    private ContaCorrente contaCorrente;

    @Before
    public void setUp() {
        hardwareMock = new HardwareMock();
        servicoRemotoMock = new ServicoRemotoMock();
        caixa = new CaixaEletronico(hardwareMock, servicoRemotoMock);
        contaCorrente = new ContaCorrente("1", "senhasecreta", new BigDecimal("100.00"));
        servicoRemotoMock.quandoChamarRecupaContaCom("1").retornar(contaCorrente);
    }

    @Test(expected = ProblemaHardwareException.class)
    public void logarComErroProblemaHardware()
            throws ContaInexistenteException, SenhaIncorretaException, ProblemaHardwareException {
        hardwareMock.lancarExceptionQuandoChamar("pegarNumeroDaContaCartao");

        caixa.logar("senhasecreta");
    }

    @Test(expected = ProblemaHardwareException.class)
    public void sacarComProblemaDeHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("entregarDinheiro");
        caixa.logar("senhasecreta");

        caixa.sacar(new BigDecimal("10.00"));
    }


    @Test(expected = ProblemaHardwareException.class)
    public void depositarComProblemaNoHardware() throws SenhaIncorretaException, ContaInexistenteException,
            ProblemaHardwareException, UsuarioNaoLogadoException {
        hardwareMock.lancarExceptionQuandoChamar("lerEnvelope");
        caixa.logar("senhasecreta");

        caixa.depositar(new BigDecimal("10.00"));
    }
}
