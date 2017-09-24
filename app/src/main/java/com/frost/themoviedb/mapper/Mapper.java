package com.frost.themoviedb.mapper;

public interface Mapper<From, To> {

    To convertFrom(From from);

    From convertTo(To to);
}