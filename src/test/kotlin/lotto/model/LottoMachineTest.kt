package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoMachineTest {
    private val manualLottoMachine =
        ManualLottoMachine(
            listOf(
                listOf(1, 2, 3, 4, 5, 6),
                listOf(1, 2, 3, 4, 5, 6),
            ),
        )
    private val manualLottos = manualLottoMachine.generate(2)
    private val automaticlottoMachine =
        object : LottoMachine {
            override fun generate(lottoSize: Int): List<Lotto> {
                return List(lottoSize) { Lotto.create(listOf(1, 2, 3, 4, 5, 6)) }
            }
        }
    private val automaticLottos = automaticlottoMachine.generate(3)

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `자동 로또의 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`(number: Int) {
        assertThat(automaticLottos).allMatch { it.contains(LottoNumber.from(number)) }
    }

    @Test
    fun `지정해준 자동 로또의 개수만큼 로또가 발행된다`() {
        val actual = automaticLottos.size
        assertThat(actual).isEqualTo(3)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `수동 로또 번호를 임의로 지정해주면 해당 번호로 로또가 발행된다`(number: Int) {
        assertThat(manualLottos).allMatch { it.contains(LottoNumber.from(number)) }
    }

    @Test
    fun `지정해준 수동 로또의 개수만큼 로또가 발행된다`() {
        val actual = manualLottos.size
        assertThat(actual).isEqualTo(2)
    }
}
