import domain.LottoGenerator
import domain.model.lotto.LottoNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoGeneratorTest {

    @Test
    fun `구입 금액에 해당하는 수의 로또를 발행한다`() {
        val lottoGenerator = LottoGenerator(
            numberOfLottos = 5
        )
        assertThat(
            lottoGenerator.generateLottos().size
        ).isEqualTo(5)
    }

    @Test
    fun `로또를 발행한다`() {
        // given
        val lottoGenerator = LottoGenerator(
            numberOfLottos = 1
        ) {
            listOf(1, 2, 3, 4, 5, 6)
        }
        // when
        val lotto = lottoGenerator.generateLottos().first()
        // then
        assertThat(lotto.numbers).isEqualTo(
            setOf(
                LottoNumber.from(1),
                LottoNumber.from(2),
                LottoNumber.from(3),
                LottoNumber.from(4),
                LottoNumber.from(5),
                LottoNumber.from(6)
            )
        )
    }
}
