package com.learn.snnipet.stream;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Snowson
 * @since 2019/3/12 12:09
 */
@Slf4j
public class StreamOperateTest {

    public static void main(String[] args) {
        ArrayList<Hotel> hotels_1 = new ArrayList<>();
        ArrayList<Hotel> hotels_2 = new ArrayList<>();

        Hotel hotel_1 = new Hotel();
        hotel_1.setName("hotel_1");
        hotel_1.setFloor(10);
        hotel_1.setCount(-1);

        ArrayList<Room> rooms_1 = new ArrayList<>();

        Room room_1 = new Room();
        room_1.setName("room_1");
        room_1.setNumber(101);
        room_1.setSize(-1);
        rooms_1.add(room_1);

        Room room_2 = new Room();
        room_2.setName("room_2");
        room_2.setNumber(102);
        room_2.setSize(-1);

        rooms_1.add(room_2);

        hotel_1.setRooms(rooms_1);

        Hotel hotel_2 = new Hotel();
        hotel_2.setName("hotel_2");
        hotel_2.setFloor(8);
        hotel_2.setCount(-1);

        ArrayList<Room> rooms_2 = new ArrayList<>();
        Room room_3 = new Room();
        room_3.setName("room_1");
        room_3.setNumber(103);
        room_3.setSize(-1);
        rooms_2.add(room_3);

        Room room_4 = new Room();
        room_4.setName("room_2");
        room_4.setNumber(104);
        room_4.setSize(-1);
        rooms_2.add(room_4);

        hotel_2.setRooms(rooms_2);

        hotels_1.add(hotel_1);
        hotels_1.add(hotel_2);

        Hotel hotel_3 = new Hotel();
        hotel_3.setName("hotel_1");
        hotel_3.setFloor(10);
        hotel_3.setCount(-1);

        ArrayList<Room> rooms_3 = new ArrayList<>();
        Room room_5 = new Room();
        room_5.setName("room_1");
        room_5.setNumber(-1);
        room_5.setSize(60);
        rooms_3.add(room_5);

        Room room_6 = new Room();
        room_6.setName("room_2");
        room_6.setNumber(-1);
        room_6.setSize(50);
        rooms_3.add(room_6);

        hotel_3.setRooms(rooms_3);

        Hotel hotel_4 = new Hotel();
        hotel_4.setName("hotel_2");
        hotel_4.setFloor(8);
        hotel_4.setCount(-1);

        ArrayList<Room> rooms_4 = new ArrayList<>();
        Room room_7 = new Room();
        room_7.setName("room_1");
        room_7.setNumber(-1);
        room_7.setSize(40);
        rooms_4.add(room_7);

        Room room_8 = new Room();
        room_8.setName("room_2");
        room_8.setNumber(-1);
        room_8.setSize(65);
        rooms_4.add(room_8);

        hotel_4.setRooms(rooms_4);

        hotels_2.add(hotel_3);
        hotels_2.add(hotel_4);

        Predicate<Map.Entry<String, Object>> judge = (x) ->
                !(x.getValue() instanceof Number) || ((Number) x.getValue()).doubleValue() >= 0;


        List<Hotel> result = Stream.of(hotels_1.stream(), hotels_2.stream())
                .flatMap(ele -> ele)
                .collect(Collectors.groupingBy(Hotel::getName))
                .entrySet().stream()
                .map(entry -> {
                    Hotel hotel1 = Hotel.create(entry.getValue().get(0));
                    List<Hotel> value = entry.getValue();
                    ArrayList<Room> srcRooms = new ArrayList<>();
                    value.forEach(hotel -> srcRooms.addAll(hotel.getRooms()));
                    List<Room> roomCollect = srcRooms.stream()
                            .collect(Collectors.groupingBy(Room::getName))
                            .entrySet().stream()
                            .map(er -> {
                                List<Room> rooms = er.getValue();
                                Map<String, Object> roomResult = new HashMap<>();
                                rooms.stream().map(r -> JSON.parseObject(JSON.toJSONString(r), Map.class))
                                        .flatMap(map -> ((Set<Map.Entry<String, Object>>) map.entrySet()).stream().filter(judge))
                                        .forEach(item -> roomResult.put(item.getKey(), item.getValue()));
                                return roomResult;
                            })
                            .map(rs -> JSON.parseObject(JSON.toJSONString(rs), Room.class))
                            .collect(Collectors.toList());
                    hotel1.setRooms(roomCollect);
                    return hotel1;
                }).collect(Collectors.toList());
        log.info("\nresult: {}", JSON.toJSONString(result));
    }
}

@Data
class Hotel implements Cloneable {
    private String name;
    private int star;
    private int floor;
    private int count;
    private List<Room> rooms;

    @Override
    public Hotel clone() throws CloneNotSupportedException {
        return (Hotel) super.clone();
    }

    public static Hotel create(Hotel hotel) {
        Hotel result = new Hotel();
        result.setCount(hotel.getCount());
        result.setFloor(hotel.getFloor());
        result.setName(hotel.getName());
        result.setStar(result.getStar());
        result.setRooms(Arrays.asList());
        return result;
    }
}

@Data
class Room {
    private String name;
    private int number;
    private int size;
}
