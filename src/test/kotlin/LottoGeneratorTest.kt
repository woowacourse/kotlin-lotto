import domain.LottoGenerator
import domain.model.lotto.Lotto
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
    fun `로또를 2개 발행한다`() {
        // given
        val lottoGenerator = LottoGenerator(
            numberOfLottos = 2
        ) {
            listOf(1, 2, 3, 4, 5, 6)
        }
        // when
        val lottos = lottoGenerator.generateLottos()
        // then
        assertThat(lottos).isEqualTo(
            listOf(
                Lotto.create(listOf(1,2,3,4,5,6)),
                Lotto.create(listOf(1,2,3,4,5,6))
            )
        )
    }
}
