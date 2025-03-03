package domain.service

import domain.model.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ManualLottoGeneratorTest {
    @Test
    fun `수동으로 로또 한 장 생성할 수 있다`() {
        val lottoGenerator =
            ManualLottoGenerator(
                listOf("1,2,3,4,5,6"),
            )
        val lotto: List<Lotto> = lottoGenerator.makeLotto(1)

        val expected: Lotto = Lotto.of(1, 2, 3, 4, 5, 6)

        assertThat(lotto.first()).isEqualTo(expected)
    }

    @Test
    fun `수동으로 로또 여러 장 생성할 수 있다`() {
        val lottoInput =
            listOf(
                "1,2,3,4,5,6",
                "10,20,30,40,41,42",
                "3,1,6,9,2,7",
                "11,22,33,14,35,26",
            )
        val lottoGenerator = ManualLottoGenerator(lottoInput)

        val lotto: List<Lotto> = lottoGenerator.makeLotto(lottoInput.size)

        assertThat(lotto.size).isEqualTo(lottoInput.size)
    }
}
