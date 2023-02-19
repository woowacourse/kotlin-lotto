import domain.LottoGenerator
import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LottoGeneratorTest {

    lateinit var lottoGenerator: LottoGenerator

    @BeforeEach
    fun setUp() {
        lottoGenerator = LottoGenerator()
    }

    @Test
    fun `로또를 수동으로 생성하기`() {
        //given
        val lottoNumbers = setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )

        //when
        val generatedLotto = lottoGenerator.generateLottosManually(lottoNumbers)

        //then
        assertThat(generatedLotto).isInstanceOf(Lotto::class.java)
    }
}