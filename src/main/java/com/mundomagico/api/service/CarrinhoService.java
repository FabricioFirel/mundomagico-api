@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private BrinquedoRepository brinquedoRepository;

    public Carrinho salvar(Carrinho carrinho) {

        for (ItemCarrinho item : carrinho.getItens()) {

            Brinquedo brinquedoBD = brinquedoRepository.findById(item.getBrinquedo().getId())
                    .orElseThrow(() -> new RuntimeException("Brinquedo não encontrado!"));

            // Verificação de estoque
            if (brinquedoBD.getEstoque() < item.getQuantidade()) {
                throw new RuntimeException(
                    "Estoque insuficiente para o brinquedo: " 
                    + brinquedoBD.getNome() 
                    + ". Disponível: " + brinquedoBD.getEstoque()
                );
            }
        }

        carrinho.setTotal(calcularTotal(carrinho));
        return carrinhoRepository.save(carrinho);
    }

    private Double calcularTotal(Carrinho carrinho) {
        return carrinho.getItens().stream()
                .mapToDouble(item ->
                        item.getBrinquedo().getPrecoPorHora()
                                * item.getHoras()
                                * item.getQuantidade()
                )
                .sum();
    }
}
