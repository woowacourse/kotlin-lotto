package lotto.domain

import lotto.generator.ManualLottoGenerator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoMachineTest {
    private val lottoMachine = LottoMachine()

    @Test
    fun `수동으로 구매할 로또 번호를 입력하면 로또 티켓을 얻을 수 있다`() {
        val input: List<List<Int>> =
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(2, 3, 4, 5, 6, 7),
            )

        val correctLotto1 =
            Lotto(
                listOf(
                    LottoNumber.from(1),
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                ),
            )
        val correctLotto2 =
            Lotto(
                listOf(
                    LottoNumber.from(2),
                    LottoNumber.from(3),
                    LottoNumber.from(4),
                    LottoNumber.from(5),
                    LottoNumber.from(6),
                    LottoNumber.from(7),
                ),
            )
        val correctOutput =
            listOf(
                correctLotto1,
                correctLotto2,
            )

        assertThat(lottoMachine.buyLottos(ManualLottoGenerator(input))).isEqualTo(correctOutput)
    }
}
