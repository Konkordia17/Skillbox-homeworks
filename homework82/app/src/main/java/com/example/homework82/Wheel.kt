package com.example.homework82

class Wheel() {
    private var pressure: Double = 0.0

    open class WheelException(message: String) : Throwable(message)

    class TooHighPressure : WheelException("Слишком большое давление")

    class TooLowPressure : WheelException("Необходимо накачать колесо")

    class IncorrectPressure : WheelException("Колесо нельзя эксплуатировать")


    fun setPressure(value: Double) {
        if (value > 10 || value < 0) throw IncorrectPressure()
        pressure = value
        check()
    }

    private fun check() {
        if (pressure < 1.6) throw TooLowPressure()
        else if (pressure > 2.5) throw TooHighPressure()
    }

}

fun main() {
    val wheel = Wheel()
    val acceptablePressure = 3.8
    val nornalPressure = 2.0
    val negativePressure = -15.3


    try {
        wheel.setPressure(acceptablePressure)
    } catch (t: Wheel.WheelException) {
        println(t.message)
    }
}

//Нормальным давлением будем считать значения от 1,6 до 2,5 атмосферы. При этом покрышка не слетит с диска, и колесо будет хорошо взаимодействовать с дорогой.


//Создайте класс колеса Wheel.
//У колеса объявите переменную давления pressure типа double.
//Будем считать, что это значение давления в атмосферах. Начальное значение задайте равным нулю. Переменная должна быть публична для чтения, но закрыта для записи.
//Внутри класса Wheel объявите три класса исключений:
//TooHighPressure — будет вызываться при слишком большом давлении;
//TooLowPressure — будет вызываться при слишком малом давлении;
//IncorrectPressure — будет вызываться при некорректном (отрицательном или слишком большом) значении давления.
//Для класса Wheel реализуйте метод setPressure (value).
//Если подаваемое давление будет отрицательным или прeвышать 10 атмосфер, функция должна вызывать соответствующее исключение.
//Добавьте метод check, имитирующий проверку колеса. Если значение давления в колесе не соответствует нормальному, необходимо вызывать соответствующее исключение.
//Если всё нормально — не делать ничего.
//Реализуйте тестовые испытания — создайте колесо, попробуйте задать три разных значения — допустимое, нормальное и отрицательное.
//Добавьте обработчик исключений. В зависимости от типа ошибки выведите в консоль результаты испытаний — произошла ли накачка колеса и можно ли его эксплуатировать.