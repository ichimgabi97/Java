import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){

        List<Resident> residentList = new ArrayList<>();
        Set<Hospital> hospitalTreeSet = new TreeSet<>(Comparator.comparing(Hospital::getName));
        Map<Resident, List<Hospital>> resPrefMap = new HashMap<>();
        List<Resident> prefList0 = new ArrayList<>();
        List<Resident> prefList1 = new ArrayList<>();
        List<Resident> prefList2 = new ArrayList<>();
        Map<Hospital, List<Resident>> hosPrefMap = new HashMap<>();

        var r = IntStream.rangeClosed(0, 3)
                         .mapToObj(i -> new Resident("R" + i) )
                         .toArray(Resident[]::new);

        var h = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i) )
                .toArray(Hospital[]::new);

        List<Hospital> target = Arrays.asList(h[0], h[2]);

        residentList.addAll(Arrays.asList(r));

        hospitalTreeSet.addAll(Arrays.asList(h));

        Collections.sort(residentList, Comparator.comparing(Resident::getName));

        resPrefMap.put(r[0], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[1], Arrays.asList(h[0], h[1], h[2]));
        resPrefMap.put(r[2], Arrays.asList(h[0], h[1]));
        resPrefMap.put(r[3], Arrays.asList(h[0], h[2]));

        prefList0.add(r[3]);
        prefList0.add(r[0]);
        prefList0.add(r[1]);
        prefList0.add(r[2]);
        hosPrefMap.put(h[0], prefList0);
        prefList1.add(r[0]);
        prefList1.add(r[2]);
        prefList1.add(r[1]);
        hosPrefMap.put(h[1], prefList1);
        prefList2.add(r[0]);
        prefList2.add(r[1]);
        prefList2.add(r[3]);
        hosPrefMap.put(h[2], prefList2);

        for(Map.Entry<Resident, List<Hospital>> entry : resPrefMap.entrySet()){
            System.out.print(entry.getKey().getName() + ": ");
            for(Hospital e : entry.getValue()){
                System.out.print(e.getName() + " ");
            }
            System.out.println("");
        }

        for (Map.Entry<Hospital, List<Resident>> entry : hosPrefMap.entrySet()){
            System.out.print(entry.getKey().getName() + ": ");
            for (Resident e : entry.getValue()){
                System.out.print(e.getName() + " ");
            }
            System.out.println("");
        }

        List<Resident> result = residentList.stream()
                .filter(res -> resPrefMap.get(res).containsAll(target))
                .collect(Collectors.toList());

        for (Resident entry : result){
            System.out.print(entry.getName() + " ");
        }
        System.out.println("");

        for (Map.Entry<Hospital, List<Resident>> entry : hosPrefMap.entrySet()){
            if (entry.getValue().get(0).getName().equals("R0")){
                System.out.print(entry.getKey().getName() + " ");
            }
        }
    }


}
