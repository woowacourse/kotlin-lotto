package model

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoMachineTest {

    @Test
    fun `구입금액이 1000 미만일 떄 예외를 던지는 지`() {
        val money = Money(999)

        val exception = assertThrows<IllegalArgumentException> { LottoMachine(money, 0) }

        assertThat(exception.message).isEqualTo("구입금액은 999보다 큰 1000원 이상이어야 합니다.")
    }

    @Test
    fun `구입금액으로 살 수 있는 수동 로또수가 아닐 떄 예외를 던지는 지`() {
        val money = Money(1_000)
        val countOfManualLotto = 10

        val exception = assertThrows<IllegalArgumentException> { LottoMachine(money, countOfManualLotto) }

        assertThat(exception.message).isEqualTo("수동 로또 10개를 1000원으로 살 수 없습니다.")
    }

    @Test
    fun `수동 로또가 수동 로또 갯수만큼 만들어지는지`() {
        val money = Money(1000)
        val countOfManualLotto = 1
        val manualLotto = Lotto(1, 2, 3, 4, 5, 6)
        val lottoMachine = LottoMachine(money, countOfManualLotto)

        lottoMachine.makeManualLotto(manualLotto)

        val actual = lottoMachine.lottoes.size

        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun `자동 로또가 자동 로또 갯수만큼 만들어지는지`() {
        val money = Money(10000)
        val countOfManualLotto = 1
        val lottoMachine = LottoMachine(money, countOfManualLotto)

        lottoMachine.makeRandomLotto()

        val actual = lottoMachine.lottoes.size

        assertThat(actual).isEqualTo(9)
    }
}
