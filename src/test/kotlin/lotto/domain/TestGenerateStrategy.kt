package lotto.domain

class TestGenerateStrategy(private val lottoNumbers: List<LottoNumber>) : GenerateStrategy {
    override fun generate(): LottoTicket {
        return LottoTicket(lottoNumbers);
    }
}
