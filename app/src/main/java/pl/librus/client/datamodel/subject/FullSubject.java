package pl.librus.client.datamodel.subject;

import com.google.common.base.Optional;

import org.immutables.value.Value;

import pl.librus.client.datamodel.Average;

/**
 * Created by robwys on 03/03/2017.
 */

@Value.Immutable
public abstract class FullSubject extends BaseSubject {

    public abstract Optional<Average> average();
}
