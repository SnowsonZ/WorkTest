#!/usr/bin/env bash

## 流程控制
### if
if test; then
  echo "process 1"
elif test; then
  echo "process 2"
elif test; then
  echo "process 3"
else
  echo "process 4"
fi

### 循环
#### 无限循环
while true; do
  echo "process.."
done
# for中的++VAR操作若放在循环体,使用++${VAR}
for (( VAR = 0; VAR < 3; ++VAR )); do
  printf "out index: %d \n" ${VAR}
  for (( VAR1 = 0; VAR1 < 5; ++VAR1 )); do
    if test ${VAR1} = 2; then
      continue;
    fi
    printf "in index: %d \n" ${VAR1}
  done
done

#### util循环
until test;do
echo "process"
done

### case
case ${1} in
  1) echo "case 1";;
  2) echo "case 2";;
  3) echo "case 3";;
  *) echo "invalid"
esac

for file_name in $(ls ../); do
  echo "${file_name} ${1}"
  # 字符串长度
  echo "length: ${#file_name}"
done

## 入参
# $*,$@区别：在双引号中$*代表将所有参数合为1个参数,
# $# 参数个数
for v in "$*" ; do
    echo ${v}
done

for v in "$@" ; do
    echo ${v}
done

echo ${1}, ${2}, ${3}
# test == []
if test ${2} = ${3} ; then
  echo "same"
fi

## 数组
# 不能使用豆号分隔
array=(1 2 3)
array_str=("one" "two1" "three")
# 输出数组所有元素
echo ${#array[*]}
# String类型 数组长度
echo ${#array_str[@]}
# String数组,单个元素长度
echo ${#array_str[0]}

## 运算符
### 算数运算符
if test ${1} == ${2}; then
    echo "num is same"
fi
### 关系运算符 -eq,-ne,-gt,-lt,-ge,-le
### 布尔运算符 ！, -o, -a
### 逻辑运算符 && ||
### 字符串运算符 =,!=,-z,-n,$

if ! test $""; then
    echo "空字符串"
fi
### 文件测试运算符
#### -s file 文件是否为空
#### -d file 是否为目录
#### -f file 是否为文件
#### -e file 检测文件是否存在（包括目录）

### 输入输出重定向
#### /dev/null 禁止输出到终端
java -version > /dev/null 2>&1