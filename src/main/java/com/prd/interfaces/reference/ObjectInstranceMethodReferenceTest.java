package com.prd.interfaces.reference;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @author prd
 * @version V1.0
 * @Description 已有实例方法引用测试
 * @date 2020-06-09 15:58
 */
@Slf4j
public class ObjectInstranceMethodReferenceTest {

    public void exec() {
        Mechanic mechanic = new Mechanic();
        Car car = new Car();

        // 内部类模式
/*        this.execute(car, new Consumer<Car>() {
            public void accept(Car c) {
                mechanic.fix(c);
            }
        });*/

        // lambda模式
//        this.execute(car, car1 -> mechanic.fix(car1));

        // 已有实例方法引用模式
        this.execute(car, mechanic::fix);
    }

    private void execute(Car car, Consumer<Car> c) {
        c.accept(car);
    }

    public static void main(String[] args) {
        new ObjectInstranceMethodReferenceTest().exec();
    }

    class Car {
        private int id;
        private String color;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
    }
    class Mechanic {
        public void fix(Car c) {
            log.info("Fixing car {}",c.getId());
        }
    }
}

