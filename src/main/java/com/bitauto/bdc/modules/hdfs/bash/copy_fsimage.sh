#!/bin/sh

if [ ! -n "$1" ];then
  echo "must has env params"
  exit 1
fi

env=$1

if [ "${env}" == "test" ];then
  src_host="192.168.15.46"
  src_dir="/dfs/namenode/current"
  src_user="root"
  dst_dir="/root/fsimage_file"
  oiv_cachedir="/root/oivcache"
elif [ "${env}" == "online" ];then
  src_host="172.20.0.13"
  src_dir="/var/dfs/nn/current"
  src_user="root"
  dst_dir="/root/fsimage_file"
  oiv_cachedir="/root/oivcache"
elif [ "${env}" == "dev" ];then
  src_host="192.168.15.46"
  src_dir="/dfs/namenode/current"
  src_user="root"
  dst_dir="/Users/weiyongxu/Documents/fsimage"
  oiv_cachedir="/Users/weiyongxu/Documents/oivcache"
else
  echo "unkonw env"
  exit 1
fi
   
fsimage_name="hdfs_fsimage_file"
delimiter_filename="hdfs_delimiter_fsimage"

find_fsimage_cmd="ls -t $src_dir |grep fsimage|grep -v md5 | head -n1"
src_filename=`ssh $src_user@$src_host "$find_fsimage_cmd"`

if [ -z "$src_filename" ];then
  echo "Not find fsimage file"
  exit 1
fi

if [ ! -d "$dst_dir" ];then
  mkdir "$dst_dir" 
fi

if [ ! -d "$oiv_cachedir" ];then
  mkdir "$oiv_cachedir" 
fi

src_file=${src_dir}/${src_filename}
scp -q $src_user@$src_host:$src_file ${dst_dir}/${fsimage_name}
if [ $? -eq 0 ];then
  echo "scp fsimage success"
else
  echo "scp fsimage fail"
  exit 1
fi

hdfs oiv -i ${dst_dir}/${fsimage_name} -p Delimited  -delimiter "," -t ${oiv_cachedir}  -o ${dst_dir}/${delimiter_filename} > /dev/null 2>&1
if [ $? -eq 0 ];then
  echo "oiv fsimage file success"
  echo "oiv delimiter fsimgge file: ${dst_dir}/${delimiter_filename}"
else
  echo "oiv fsimage file fail"
  exit 1
fi
